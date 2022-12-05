package br.ufrn.imd.pdvlp2.log.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import br.ufrn.imd.pdvlp2.core.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@Document("Log")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogModel extends AbstractModel {

    private String subject;
    private String predicate;
    private String object;
    private String reason;
    private LocalDateTime date;

}
