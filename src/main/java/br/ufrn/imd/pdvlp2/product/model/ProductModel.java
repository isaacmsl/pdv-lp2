package br.ufrn.imd.pdvlp2.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("Product")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends AbstractModel {

    private String name;
    private int quantity;
    private double price;
    
    @Indexed(unique = true)
    private String barcode;

}
