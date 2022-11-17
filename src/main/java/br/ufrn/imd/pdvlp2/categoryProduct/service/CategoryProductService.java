package br.ufrn.imd.pdvlp2.categoryProduct.service;

import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.categoryProduct.model.CategoryProductModel;
import br.ufrn.imd.pdvlp2.categoryProduct.repository.CategoryProductRepository;
import br.ufrn.imd.pdvlp2.core.service.AbstractService;

@Service
public class CategoryProductService extends AbstractService<CategoryProductModel, CategoryProductRepository> {
}
