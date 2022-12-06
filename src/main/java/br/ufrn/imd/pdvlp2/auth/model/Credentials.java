package br.ufrn.imd.pdvlp2.auth.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken convertTo(){
        return  new UsernamePasswordAuthenticationToken(username, password);
    }
}
