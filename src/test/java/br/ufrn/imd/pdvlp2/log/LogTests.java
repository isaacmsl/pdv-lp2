package br.ufrn.imd.pdvlp2.log;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.log.model.LogModel;
import br.ufrn.imd.pdvlp2.log.repository.LogRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LogTests {

    @Autowired
    LogRepository repository;
    LogModel log;

    @Before
    public void setUp() throws Exception {
        log = repository.save(new LogModel("System", "has failed loading", "products", "no products", LocalDateTime.now()));
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(log.getId()).isPresent());
    }

    @Test
    public void shouldFindBySubject() {
        Assert.assertEquals(false, repository.findBySubject(log.getSubject()).isEmpty());
    }

    @Test
    public void shouldFindByObject() {
        Assert.assertEquals(false, repository.findByObject(log.getObject()).isEmpty());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}