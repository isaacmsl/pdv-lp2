package br.ufrn.imd.pdvlp2.product.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;

@Service
public class ProductService extends AbstractService<ProductModel, ProductRepository> {
}
