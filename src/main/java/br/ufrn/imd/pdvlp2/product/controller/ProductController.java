package br.ufrn.imd.pdvlp2.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController extends AbstractController<ProductModel, ProductService>{
    
}
