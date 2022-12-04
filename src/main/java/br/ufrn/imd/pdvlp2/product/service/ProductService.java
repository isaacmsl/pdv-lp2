package br.ufrn.imd.pdvlp2.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;

@Service
public class ProductService extends AbstractService<ProductModel, ProductRepository> {
    @Autowired
    ProductRepository repository;

    public Optional<ProductModel> findByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }
    
    public Optional<ProductModel> findByName(String name) {
        return repository.findByName(name);
    }

    public List<ProductModel> findByNameRegex(String name) {
        return repository.findByNameRegex(name);
    }
}
