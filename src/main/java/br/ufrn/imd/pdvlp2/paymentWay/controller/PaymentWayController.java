package br.ufrn.imd.pdvlp2.paymentWay.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.paymentWay.model.PaymentWayModel;
import br.ufrn.imd.pdvlp2.paymentWay.service.PaymentWayService;

@RestController
@RequestMapping("paymentWays")
public class PaymentWayController extends AbstractController<PaymentWayModel, PaymentWayService>{
    @GetMapping
    public ResponseEntity<PaymentWayModel> findByName(@RequestParam String name) {
        Optional<PaymentWayModel> paymentWay = service.findByName(name);

        if (!paymentWay.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(paymentWay.get());
    }

    @GetMapping("/regex")
    public ResponseEntity<List<PaymentWayModel>> findByNameRegex(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findByNameRegex(name));
    }
}
