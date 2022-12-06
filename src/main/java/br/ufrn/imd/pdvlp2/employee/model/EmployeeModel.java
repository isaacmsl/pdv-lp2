package br.ufrn.imd.pdvlp2.employee.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufrn.imd.pdvlp2.auth.model.Role;
import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("Employee")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel extends AbstractModel implements UserDetails {
    @Builder.Default
    private boolean enabled = true;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Builder.Default
    private Set<Role> authorities = new HashSet<>();

    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
