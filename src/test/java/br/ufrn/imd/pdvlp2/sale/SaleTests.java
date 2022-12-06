package br.ufrn.imd.pdvlp2.sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufrn.imd.pdvlp2.sale.model.SaleModel;
import br.ufrn.imd.pdvlp2.sale.repository.SaleRepository;
import br.ufrn.imd.pdvlp2.employee.model.Address;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
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
    EmployeeModel employee;

    @Before
    public void setUp() throws Exception {
        Address address = new Address("Rua do Lindo", "Neópolis", "42", "Natal");
        
        employee = new EmployeeModel(
            true,
            "isaacmsl",
            "123",
            new HashSet<>(),
            "Isaac Lourenço",
            "isaac@imd.ufrn.br",
            "84999999999",
            address,
            LocalDate.parse("2001-07-27")
        );

        product1 = productRepository.save(new ProductModel("Ruffles", 5, 7.85, "12341242133"));
        product2 = productRepository.save(new ProductModel("Pippos", 10, 4.50, "34412390421"));
        List<ProductModel> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        paymentWay = new PaymentWayModel("Pix", 0.01);

        model = repository.save(new SaleModel(employee, paymentWay, products));
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
        productRepository.deleteAll();
        paymentWayRepository.deleteAll();
    }

    @Test
    public void shouldFindAllByEmployeeName() {
        Assert.assertEquals(false, repository.findAllByEmployeeName(employee.getName()).isEmpty());
    }

    @Test
    public void shouldBeNotEmpty() {
        Assert.assertEquals(true, repository.count() > 0);
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(model.getId()).isPresent());
    }

    // @Test
    // public void shouldDecreaseQuantityProducts() {
    //     Optional<ProductModel> productAfter1 = productRepository.findById(product1.getId());
    //     Optional<ProductModel> productAfter2 = productRepository.findById(product2.getId());
        
    //     Assert.assertEquals(true, productAfter1.isPresent());
    //     Assert.assertEquals(true, productAfter2.isPresent());

    //     Assert.assertEquals(1, product1.getQuantity() - productAfter1.get().getQuantity());
    //     Assert.assertEquals(1, product2.getQuantity() - productAfter2.get().getQuantity());
    // }

    @Test
    public void shouldTotalPriceBeFoldSumProductsPlusTaxes() {
        double sumProducts = product1.getPrice() + product2.getPrice();
        double tax = model.getPaymentWay().getTax();
        double totalPricePlusTaxes = sumProducts * (1.0 + tax);

        Assert.assertEquals(true, totalPricePlusTaxes == model.getTotal());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}