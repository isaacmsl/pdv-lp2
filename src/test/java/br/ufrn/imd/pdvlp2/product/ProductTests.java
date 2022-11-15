package br.ufrn.imd.pdvlp2.product;

import org.junit.After;
import org.junit.Assert;

// import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.product.controller.ProductController;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductTests {

    @Autowired
    ProductRepository productRepository; 
    ProductModel productTest1;

    @Before
    public void setUp() throws Exception {
        productTest1 = productRepository.save(new ProductModel("chocolate_test", 5, "food"));
        System.out.println(productTest1.getName());
    }

    @Test
    public void shouldBeNotEmpty() {
        Assert.assertNotNull(productRepository.findById(productTest1.id));
    }

     
    @Before
    public void deleteAll() throws Exception{
    productRepository.deleteAll();
    }

    @Test
    public void shouldBeEmpty(){
    Assert.assertEquals(0, productRepository.count());
    }

}