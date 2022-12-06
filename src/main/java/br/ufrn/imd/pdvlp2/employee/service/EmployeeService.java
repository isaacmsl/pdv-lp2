package br.ufrn.imd.pdvlp2.employee.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.core.service.AbstractService;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.employee.repository.EmployeeRepository;

@Service
@NoRepositoryBean
public class EmployeeService extends AbstractService<EmployeeModel, EmployeeRepository> {

    @Autowired
    EmployeeRepository repository;
    
    public Optional<EmployeeModel> findByUsername(String username) {
        return repository.findByUsername(username);
    }
    
    public List<EmployeeModel> findAllByName(String name) {
        return repository.findAllByName(name);
    }
    
    public List<EmployeeModel> findAllByBirthdate(LocalDate birthdate) {
        return repository.findAllByBirthdate(birthdate);
    }

}
