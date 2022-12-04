package br.ufrn.imd.pdvlp2.log.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.exceptions.ProductSoldOffException;
import br.ufrn.imd.pdvlp2.log.service.LogService;
import br.ufrn.imd.pdvlp2.log.model.LogModel;

@RestController
@RequestMapping("logs")
public class LogController extends AbstractController<LogModel, LogService>{
    @GetMapping("/subject")
    public ResponseEntity<List<LogModel>> findBySubject(@RequestParam String subject) {
        return ResponseEntity.ok().body(service.findBySubject(subject));
    }

    @GetMapping("/object")
    public ResponseEntity<List<LogModel>> findByObject(@RequestParam String object) {
        return ResponseEntity.ok().body(service.findByObject(object));
    }
    
    @PostMapping
    @Override
    public ResponseEntity<LogModel> post(@RequestBody LogModel saveModel) {
        LogModel result = null;
        
        try {
            saveModel.setDate(LocalDateTime.now());
            result = (LogModel) service.save(saveModel);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
