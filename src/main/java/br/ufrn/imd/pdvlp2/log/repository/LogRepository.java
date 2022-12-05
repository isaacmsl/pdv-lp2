package br.ufrn.imd.pdvlp2.log.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.log.model.LogModel;

@Repository
public interface LogRepository extends AbstractRepository<LogModel> {

    List<LogModel> findBySubject(String subject);
    List<LogModel> findByObject(String object);

}
