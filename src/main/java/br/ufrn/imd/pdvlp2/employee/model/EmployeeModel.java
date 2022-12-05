package br.ufrn.imd.pdvlp2.employee.model;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;

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
public class EmployeeModel extends AbstractModel {
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDate birthdate;

    private Boolean isManager;
}
