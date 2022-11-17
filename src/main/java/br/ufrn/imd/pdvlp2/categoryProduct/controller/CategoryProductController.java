package br.ufrn.imd.pdvlp2.categoryProduct.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.categoryProduct.model.CategoryProductModel;
import br.ufrn.imd.pdvlp2.categoryProduct.service.CategoryProductService;
import br.ufrn.imd.pdvlp2.core.controller.AbstractController;

@RestController
@RequestMapping("categoriesProducts")
public class CategoryProductController extends AbstractController<CategoryProductModel, CategoryProductService>{
    
}
