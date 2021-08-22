package com.tony.pandemic.builders;

import com.tony.pandemic.hospital.Hospital;

import java.text.ParseException;
import java.time.LocalDateTime;

public class HospitalBuilder {

    public static Hospital createHospital() throws ParseException {
        return Hospital.builder()
                .name("Hospital Samaritano")
                .address("Av. Santa Júlia")
                .cnpj("23425345")
                .percentageOfOccupation(60)
                .localization(null)
                .registrationTime(LocalDateTime.now())
                .resource(null)
                .build();
    }
}
