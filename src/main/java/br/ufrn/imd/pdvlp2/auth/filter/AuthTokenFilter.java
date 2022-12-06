package br.ufrn.imd.pdvlp2.auth.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.ufrn.imd.pdvlp2.auth.service.TokenService;
import br.ufrn.imd.pdvlp2.employee.model.EmployeeModel;
import br.ufrn.imd.pdvlp2.employee.repository.EmployeeRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private EmployeeRepository employeeRepository;

    public AuthTokenFilter(TokenService tokenService, EmployeeRepository employeeRepository){
        this.tokenService = tokenService;
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        boolean validToken = tokenService.isValid(token);
        if(validToken){
            authClient(token);
        }
        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }

    private void authClient(String token){
        String userId = tokenService.getUserId(token);
        Optional<EmployeeModel> employee = employeeRepository.findById(userId);

        if (!employee.isPresent()) {
            return;
        }
        
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(employee.get(), null, employee.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}

