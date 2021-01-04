package com.tony.pandemic.hospital;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class HospitalDTO {

    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    private LocalDateTime registrationTime;

    public HospitalDTO(Hospital hospital) {
        id = hospital.getId();
        name = hospital.getName();
        address = hospital.getAddress();
        cnpj = hospital.getCnpj();
        percentageOfOccupation = hospital.getPercentageOfOccupation();
    }

}
