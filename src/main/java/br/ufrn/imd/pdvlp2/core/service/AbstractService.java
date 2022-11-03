package br.ufrn.imd.pdvlp2.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;

public abstract class AbstractService <M extends AbstractModel, R extends AbstractRepository<M>> {
    @Autowired
    private R repository;

    public List<M> findAll() {
        return repository.findAll();
    }
    
    public Optional<M> findById(String id) {
        return repository.findById(id);
    }

    public synchronized M save(M model) {
        M savedModel = repository.save(model);
        return savedModel;
    }

    public abstract M patch(M model, M saveModel);

    public void delete(M model) {
        repository.delete(model);
    }

}
