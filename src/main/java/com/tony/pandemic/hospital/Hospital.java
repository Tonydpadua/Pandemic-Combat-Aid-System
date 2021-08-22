package com.tony.pandemic.hospital;

import com.tony.pandemic.hospital.dto.HospitalDTO;
import com.tony.pandemic.hospital.dto.HospitalNewDTO;
import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    private LocalDateTime registrationTime;

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

    public Hospital(Long id, String name, String address, String cnpj, double percentageOfOccupation, Localization localization, Resource resource) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.percentageOfOccupation = percentageOfOccupation;
        this.localization = localization;
        this.resource = resource;
    }

    public static Hospital from(HospitalDTO hospitalDTO) {
        return Hospital.builder()
                .id(hospitalDTO.getId())
                .name(hospitalDTO.getName())
                .address(hospitalDTO.getAddress())
                .cnpj(hospitalDTO.getCnpj())
                .percentageOfOccupation(hospitalDTO.getPercentageOfOccupation())
                .build();
    }

    public static Hospital from(HospitalNewDTO hospitalNewDTO) {
        return Hospital.builder()
                .name(hospitalNewDTO.getName())
                .address(hospitalNewDTO.getAddress())
                .cnpj(hospitalNewDTO.getCnpj())
                .percentageOfOccupation(hospitalNewDTO.getPercentageOfOccupation())
                .localization(Localization.from(hospitalNewDTO.getLocalization()))
                .registrationTime(hospitalNewDTO.getRegistrationTime())
                .resource(hospitalNewDTO.getResource())
                .build();
    }
}
