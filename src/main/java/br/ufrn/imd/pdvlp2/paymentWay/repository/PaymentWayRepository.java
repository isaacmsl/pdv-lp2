package br.ufrn.imd.pdvlp2.paymentWay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;

public interface PaymentWayRepository extends AbstractRepository<PaymentWayModel> {
    Optional<PaymentWayModel> findByName(String name);

    @Query("{name:{$regex: ?0,$options:'i'}}")
    List<PaymentWayModel> findByNameRegex(String name);

    List<PaymentWayModel> findAll();
}
