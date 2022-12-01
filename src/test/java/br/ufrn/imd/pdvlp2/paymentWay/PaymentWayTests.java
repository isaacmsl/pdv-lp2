package br.ufrn.imd.pdvlp2.paymentWay;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;
import br.ufrn.imd.pdvlp2.paymentWay.repository.PaymentWayRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PaymentWayTests {

    @Autowired
    PaymentWayRepository repository;
    PaymentWayModel model;

    @Before
    public void setUp() throws Exception {
        model = repository.save(new PaymentWayModel("Pix", 0.01));
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(model.getId()).isPresent());
    }

    @Test
    public void shouldFindByName() {
        Assert.assertEquals(true, repository.findByName(model.getName()).isPresent());
    }

    @Test
    public void shouldFindByNameRegex() {
        repository.save(new PaymentWayModel("Pixels", 0.04));
        repository.save(new PaymentWayModel("Neopix", 0.01));
        Assert.assertEquals(3, repository.findByNameRegex("pIx").size());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}