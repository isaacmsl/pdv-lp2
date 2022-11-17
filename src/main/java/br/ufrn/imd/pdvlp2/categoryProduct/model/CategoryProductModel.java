package br.ufrn.imd.pdvlp2.categoryProduct.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("CategoryProduct")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductModel extends AbstractModel {

    private String name;

    @OneToMany
    @JoinColumn(name="category_product_id")
    List<ProductModel> products;

}
