package br.ufrn.imd.pdvlp2.employee;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.employee.model.Address;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.employee.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EmployeeTests {

    @Autowired
    EmployeeRepository repository;
    EmployeeModel employee;

    @Before
    public void setUp() throws Exception {
        Address address = new Address("Rua do Lindo", "Neópolis", "42", "Natal");

        employee = repository.save(
            new EmployeeModel(
                true,
                "isaacmsl",
                "123",
                new HashSet<>(),
                "Isaac Lourenço",
                "isaac@imd.ufrn.br",
                "84999999999",
                address,
                LocalDate.parse("2001-07-27")
            )
        );
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(employee.getId()).isPresent());
    }

    @Test
    public void shouldFindAllByName() {
        Assert.assertEquals(false, repository.findAllByName(employee.getName()).isEmpty());
    }

    @Test
    public void shouldFindAllByBirthdate() {
        Assert.assertEquals(false, repository.findAllByBirthdate(employee.getBirthdate()).isEmpty());
    }

    @Test
    public void shouldFindAllByBirthdateString() {
        Assert.assertEquals(false, repository.findAllByBirthdate(LocalDate.parse("2001-07-27")).isEmpty());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}