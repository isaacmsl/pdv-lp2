package br.ufrn.imd.pdvlp2.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.ufrn.imd.pdvlp2.auth.filter.AuthTokenFilter;
import br.ufrn.imd.pdvlp2.auth.service.TokenService;
import br.ufrn.imd.pdvlp2.employee.repository.EmployeeRepository;
import br.ufrn.imd.pdvlp2.employee.service.EmployeeDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeDetailsServiceImpl userDetailsService;

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers(HttpMethod.POST, "/auth/signIn").permitAll()
				.antMatchers("/products").permitAll()
				.antMatchers("/employees/findAll").permitAll()
				.antMatchers(HttpMethod.POST, "/employees").permitAll()
				.anyRequest().authenticated()
			)
			.cors().and()
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(new AuthTokenFilter(tokenService, employeeRepository), UsernamePasswordAuthenticationFilter.class)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
}
