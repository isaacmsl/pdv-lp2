package br.ufrn.imd.pdvlp2.paymentWay.model;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("PaymentWay")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWayModel extends AbstractModel {

    private String name;
    private double tax;

}
