package com.tony.pandemic.hospital.dto;

import com.tony.pandemic.hospital.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTO {


    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    public static HospitalDTO from(Hospital hospital) {
        return HospitalDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .cnpj(hospital.getCnpj())
                .percentageOfOccupation(hospital.getPercentageOfOccupation())
                .build();
    }

    public static Page<HospitalDTO> from(Page<Hospital> pages){
        return pages.map(HospitalDTO::from);
    }
}
