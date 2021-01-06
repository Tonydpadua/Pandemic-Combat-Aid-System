package com.tony.pandemic.hospital;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HospitalDTO {

    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    public HospitalDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.cnpj = hospital.getCnpj();
        this.percentageOfOccupation = hospital.getPercentageOfOccupation();
    }
}
