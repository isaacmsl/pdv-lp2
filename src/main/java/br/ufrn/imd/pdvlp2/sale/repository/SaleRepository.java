package br.ufrn.imd.pdvlp2.sale.repository;

import org.springframework.stereotype.Repository;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.sale.model.SaleModel;

@Repository
public interface SaleRepository extends AbstractRepository<SaleModel> {
}
