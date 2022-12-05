package br.ufrn.imd.pdvlp2.sale.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("Sale")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleModel extends AbstractModel {
    
    @ManyToOne
    private EmployeeModel employee;

    @ManyToOne
    private PaymentWayModel paymentWay;
    
    @OneToMany
    @JoinColumn(name="sale_id")
    List<ProductModel> products;

    public double getTotal() {
        double totalProducts = products.stream().reduce(0.0, (subTotal, element) -> subTotal + element.getPrice(), Double::sum);
        return totalProducts * (1 + paymentWay.getTax());
    }


}
