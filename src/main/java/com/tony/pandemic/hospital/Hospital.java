package com.tony.pandemic.hospital;

import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    @OneToOne(cascade = CascadeType.ALL)
    private Localization localization;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Resource resource;

    public Hospital(String name, String address, String cnpj, double percentageOfOccupation) {
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.percentageOfOccupation = percentageOfOccupation;
    }
}
