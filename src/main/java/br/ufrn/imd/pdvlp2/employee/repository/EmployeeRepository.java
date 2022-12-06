package br.ufrn.imd.pdvlp2.employee.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.ufrn.imd.pdvlp2.core.repository.AbstractRepository;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends AbstractRepository<EmployeeModel> {

    Optional<EmployeeModel> findByUsername(String username);
    List<EmployeeModel> findAllByName(String name);
    List<EmployeeModel> findAllByBirthdate(LocalDate birthdate);

}
