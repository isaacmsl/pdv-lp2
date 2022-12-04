package br.ufrn.imd.pdvlp2.sale.service;


import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.exceptions.ProductSoldOffException;
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

        return repository.save(model);
    }
}
