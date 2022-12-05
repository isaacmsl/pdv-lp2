package br.ufrn.imd.pdvlp2.sale.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.exceptions.ProductSoldOffException;
import br.ufrn.imd.pdvlp2.sale.service.SaleService;
import br.ufrn.imd.pdvlp2.sale.model.SaleModel;

@RestController
@RequestMapping("sales")
public class SaleController extends AbstractController<SaleModel, SaleService>{
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<List<SaleModel>> findAllByName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findAllByEmployeeName(name));
    }

    @PostMapping
    @Override
    public ResponseEntity<SaleModel> post(@RequestBody SaleModel saveModel) {
        SaleModel result = null;
        
        try {
            result = (SaleModel) service.save(saveModel);
        } catch (ProductSoldOffException exception) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
