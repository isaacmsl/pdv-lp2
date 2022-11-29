package br.ufrn.imd.pdvlp2.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.product.model.ProductModel;

public interface ProductRepository extends AbstractRepository<ProductModel> {
    @Autowired
    @Query("{name:'?0'}")
    Optional<ProductModel> findByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    List<ProductModel> findAll(String category);
}
