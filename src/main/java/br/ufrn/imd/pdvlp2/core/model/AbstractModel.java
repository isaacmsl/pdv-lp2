package br.ufrn.imd.pdvlp2.core.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractModel {
    @Id
    public String id;

    @Column(nullable = false)
    private Boolean isActive = true;
}
