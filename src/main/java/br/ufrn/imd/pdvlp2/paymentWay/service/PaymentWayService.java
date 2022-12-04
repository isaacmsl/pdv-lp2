package br.ufrn.imd.pdvlp2.paymentWay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;
import br.ufrn.imd.pdvlp2.paymentWay.repository.PaymentWayRepository;
@Service
public class PaymentWayService extends AbstractService<PaymentWayModel, PaymentWayRepository> {
    @Autowired
    PaymentWayRepository repository;

    public Optional<PaymentWayModel> findByName(String name) {
        return repository.findByName(name);
    }

    public List<PaymentWayModel> findByNameRegex(String name) {
        return repository.findByNameRegex(name);
    }
}
