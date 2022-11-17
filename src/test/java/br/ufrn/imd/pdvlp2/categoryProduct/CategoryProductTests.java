package br.ufrn.imd.pdvlp2.categoryProduct;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.categoryProduct.model.CategoryProductModel;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;
import br.ufrn.imd.pdvlp2.categoryProduct.repository.CategoryProductRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryProductTests {

    @Autowired
    CategoryProductRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    
    CategoryProductModel category;
    ProductModel product;

    @Before
    public void setUp() throws Exception {
        product = productRepository.save(new ProductModel("Ruffles", 5, 10));
        List<ProductModel> products = new ArrayList<>();
        products.add(product);
        category = new CategoryProductModel("Pop corn", products);
        categoryRepository.save(category);
        category = categoryRepository.findById(category.getId()).get();
    }

    @Test
    public void shouldBeNotEmpty() {
        Assert.assertNotNull(category);
    }
    @Test
    public void shouldHaveProduct() {
        Assert.assertEquals(1, category.getProducts().size());
    }

    @Before
    public void deleteAll() throws Exception {
        categoryRepository.deleteAll();
        productRepository.delete(product);
    }

    @Test
    public void shouldBeEmpty() {
        Assert.assertEquals(0, categoryRepository.count());
    }

}