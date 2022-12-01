package br.ufrn.imd.pdvlp2.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.Sale.model.SaleModel;
import br.ufrn.imd.pdvlp2.Sale.repository.SaleRepository;
import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;
import br.ufrn.imd.pdvlp2.paymentWay.repository.PaymentWayRepository;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import br.ufrn.imd.pdvlp2.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SaleTests {

    @Autowired
    SaleRepository repository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PaymentWayRepository paymentWayRepository;

    SaleModel model;
    ProductModel product1, product2;
    PaymentWayModel paymentWay;

    @Before
    public void setUp() throws Exception {
        product1 = productRepository.save(new ProductModel("Ruffles", 5, 7.85));
        product2 = productRepository.save(new ProductModel("Pippos", 10, 4.50));
        List<ProductModel> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        paymentWay = new PaymentWayModel("Pix", 0.01);

        model = repository.save(new SaleModel(paymentWay, products));
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldBeNotEmpty() {
        Assert.assertEquals(true, repository.count() > 0);
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(model.getId()).isPresent());
    }

    @Test
    public void shouldDecreaseQuantityProducts() {
        Optional<ProductModel> productAfter1 = productRepository.findById(product1.getId());
        Optional<ProductModel> productAfter2 = productRepository.findById(product2.getId());
        
        Assert.assertEquals(true, productAfter1.isPresent());
        Assert.assertEquals(true, productAfter2.isPresent());

        Assert.assertEquals(1, product1.getQuantity() - productAfter1.get().getQuantity());
        Assert.assertEquals(1, product2.getQuantity() - productAfter2.get().getQuantity());
    }

    @Test
    public void shouldTotalPriceBeFoldSumProductsPlusTaxes() {
        double sumProducts = product1.getPrice() + product2.getPrice();
        double tax = model.getPaymentWay().getTax();
        double totalPricePlusTaxes = sumProducts * (1.0 + tax);

        Assert.assertEquals(totalPricePlusTaxes, model.getTotalPrice());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}