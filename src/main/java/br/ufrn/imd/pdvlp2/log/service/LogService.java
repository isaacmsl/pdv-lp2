package br.ufrn.imd.pdvlp2.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.log.model.LogModel;
import br.ufrn.imd.pdvlp2.log.repository.LogRepository;

@Service
@NoRepositoryBean
public class LogService extends AbstractService<LogModel, LogRepository> {

    @Autowired
    LogRepository repository;
    
    public List<LogModel> findBySubject(String subject) {
        return repository.findBySubject(subject);
    }
    
    public List<LogModel> findByObject(String object) {
        return repository.findByObject(object);
    }

}
