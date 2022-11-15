package br.ufrn.imd.pdvlp2.product.model;

import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Data; 

@Document("Product")
@Data
@AllArgsConstructor
public class ProductModel extends AbstractModel {

    private String name;
    private int quantity;
    private String category;

}
