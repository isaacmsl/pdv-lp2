package br.ufrn.imd.pdvlp2.employee.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.core.controller.AbstractController;
import br.ufrn.imd.pdvlp2.employee.service.EmployeeService;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;

@RestController
@RequestMapping("employees")
public class EmployeeController extends AbstractController<EmployeeModel, EmployeeService>{
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<List<EmployeeModel>> findAllByName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findAllByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"birthdate"})
    public ResponseEntity<List<EmployeeModel>> findAllByBirthdate(@RequestParam String birthdate) {
        return ResponseEntity.ok().body(service.findAllByBirthdate(LocalDate.parse(birthdate)));
    }

    @Override
    @PostMapping
    public ResponseEntity<EmployeeModel> post(@RequestBody EmployeeModel saveModel) {
        saveModel.setPassword(new BCryptPasswordEncoder().encode(saveModel.getPassword()));
        return super.post(saveModel);
    }
}
