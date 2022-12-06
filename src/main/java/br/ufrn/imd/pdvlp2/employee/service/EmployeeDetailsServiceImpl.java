package br.ufrn.imd.pdvlp2.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.employee.repository.EmployeeRepository;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeModel> opEmployee = repository.findByUsername(username);
        
        if (!opEmployee.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return opEmployee.get();
    }
    
}
