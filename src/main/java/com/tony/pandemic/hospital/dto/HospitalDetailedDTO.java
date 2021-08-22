package com.tony.pandemic.hospital.dto;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.localization.dto.LocalizationDTO;
import com.tony.pandemic.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetailedDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    @Length(min=5,max=100,message="The length must be between 5 and 100 characters")
    private String name;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Cnpj is mandatory")
    private String cnpj;

    @NotNull
    private double percentageOfOccupation;

    private LocalDateTime registrationTime;

    private LocalizationDTO localization;

    private Resource resource;

    public static HospitalDetailedDTO from(Hospital hospital) {
        return HospitalDetailedDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .cnpj(hospital.getCnpj())
                .percentageOfOccupation(hospital.getPercentageOfOccupation())
                .localization(LocalizationDTO.from(hospital.getLocalization()))
                .registrationTime(hospital.getRegistrationTime())
                .resource(hospital.getResource())
                .build();
    }
}
