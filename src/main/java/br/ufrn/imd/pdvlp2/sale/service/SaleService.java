package br.ufrn.imd.pdvlp2.sale.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.exceptions.ProductSoldOffException;
import br.ufrn.imd.pdvlp2.log.model.LogModel;
import br.ufrn.imd.pdvlp2.log.repository.LogRepository;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;
import br.ufrn.imd.pdvlp2.sale.model.SaleModel;
import br.ufrn.imd.pdvlp2.sale.repository.SaleRepository;
@Service
@NoRepositoryBean
public class SaleService extends AbstractService<SaleModel, SaleRepository> {
    @Autowired
    SaleRepository repository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    LogRepository log;

    public List<SaleModel> findAllByEmployeeName(String name) {
        return repository.findAllByEmployeeName(name);
    }


    @Override
    public synchronized SaleModel save(SaleModel model) throws ProductSoldOffException {
        for(ProductModel product : model.getProducts()) {
            Optional<ProductModel> foundProduct = productRepository.findById(product.getId());
            
            if (!foundProduct.isPresent()) {
                continue;
            }

            product = foundProduct.get();
            if (product.getQuantity() <= 0) {
                throw new ProductSoldOffException("Quantity of product reached zero");
            }
        }

        for (int i = 0; i < model.getProducts().size(); i++) {
            ProductModel product = model.getProducts().get(i);

            Optional<ProductModel> foundProduct = productRepository.findById(product.getId());
            
            if (!foundProduct.isPresent()) {
                continue;
            }
            
            product = foundProduct.get();

            product.setQuantity(product.getQuantity() - 1);
            product = productRepository.save(product);
            model.getProducts().set(i, product);
        }

        SaleModel savedModel = repository.save(model);
        log.save(new LogModel("System", "has created/updated a " + model.getClass().getSimpleName(), model.getId(), null, LocalDateTime.now()));
        return savedModel;
    }
}
