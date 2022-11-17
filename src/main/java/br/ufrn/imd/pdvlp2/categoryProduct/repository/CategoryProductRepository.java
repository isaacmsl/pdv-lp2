package br.ufrn.imd.pdvlp2.categoryProduct.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;

import br.ufrn.imd.pdvlp2.categoryProduct.model.CategoryProductModel;
import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;

public interface CategoryProductRepository extends AbstractRepository<CategoryProductModel> {
    @Autowired
    @Query("{name:'?0'}")
    ProductModel findItemByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    List<ProductModel> findAll(String category);
}
