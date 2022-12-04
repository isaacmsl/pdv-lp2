package br.ufrn.imd.pdvlp2.product;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

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
        productTest1 = productRepository.save(new ProductModel("chocolate_test", 5, 100, "7897256257653"));
    }

    @After
    public void deleteAll() throws Exception {
        productRepository.deleteAll();
    }

    @Test
    public void shouldFindByBarcode() {
        Assert.assertEquals(true, productRepository.findByBarcode(productTest1.getBarcode()).isPresent());
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, productRepository.findById(productTest1.id).isPresent());
    }

    @Test
    public void shouldFindByName() {
        Assert.assertEquals(true, productRepository.findByName(productTest1.getName()).isPresent());
    }

    @Test
    public void shouldFindByNameRegex() {
        productRepository.save(new ProductModel("ballchoco", 5, 10, "123123123"));
        productRepository.save(new ProductModel("Chocolatto", 50, 100, "1231234"));
        Assert.assertEquals(3, productRepository.findByNameRegex("choco").size());
    }

    @Test
    public void shouldBeEmpty() {
        productRepository.deleteAll();
        Assert.assertEquals(0, productRepository.count());
    }

}