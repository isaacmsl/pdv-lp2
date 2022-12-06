package br.ufrn.imd.pdvlp2.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pdvlp2.auth.model.Credentials;
import br.ufrn.imd.pdvlp2.auth.service.TokenService;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.employee.service.EmployeeService;
import br.ufrn.imd.pdvlp2.exceptions.UnauthorizedUserException;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/signIn",  consumes = {"application/json"})
    public String signIn(@RequestBody Credentials credentials) throws UnauthorizedUserException {
        Optional<EmployeeModel> user = employeeService.findByUsername(credentials.getUsername());
        if (user.isEmpty() || !user.get().isEnabled()) {
            throw new UnauthorizedUserException("UNAUTHORIZED_USER");
        }

        UsernamePasswordAuthenticationToken loginData = credentials.convertTo();

        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authenticate);
            return token;
        }catch (Exception e){
            e.printStackTrace();
            throw new UnauthorizedUserException("BAD_CREDENTIALS");
        }
    }

}
