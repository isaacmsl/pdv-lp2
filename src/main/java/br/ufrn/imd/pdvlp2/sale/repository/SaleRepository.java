package br.ufrn.imd.pdvlp2.sale.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.sale.model.SaleModel;

@Repository
public interface SaleRepository extends AbstractRepository<SaleModel> {
    public List<SaleModel> findAllByEmployeeName(String name);
}
