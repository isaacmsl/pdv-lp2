package br.ufrn.imd.pdvlp2.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;

@NoRepositoryBean
public interface AbstractRepository <M extends AbstractModel> extends MongoRepository<M, String> {
}
