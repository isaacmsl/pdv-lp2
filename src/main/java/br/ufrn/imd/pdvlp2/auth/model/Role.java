package br.ufrn.imd.pdvlp2.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

  public static final String USER_ADMIN = "USER_ADMIN";
  private String authority;

}