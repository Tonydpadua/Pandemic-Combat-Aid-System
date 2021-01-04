package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TimeOccupatedHospital implements ITimeOccupatedHospital {

    private IHospitalRepository repository;

    @Override
    public String moreOccupatedMostTime() {
        Hospital result = new Hospital();
        result.setRegistrationTime(LocalDateTime.now());
        List<Hospital> list = this.repository.findAll().stream()
                .filter(hospital -> hospital.getPercentageOfOccupation() >= 90.0).collect(Collectors.toList());
        for (Hospital hospital : list) {
            if (hospital.getRegistrationTime().isBefore(result.getRegistrationTime())) {
                result = hospital;
            }
        }
        return result.getName();
    }

    @Override
    public String lessOccupatedMostTime() {
        Hospital result = new Hospital();
        result.setRegistrationTime(LocalDateTime.now());
        List<Hospital> list = this.repository.findAll().stream()
                .filter(hospital -> hospital.getPercentageOfOccupation() < 90.0).collect(Collectors.toList());
        for (Hospital hospital : list) {
            if (hospital.getRegistrationTime().isBefore(result.getRegistrationTime())) {
                result = hospital;
            }
        }
        return result.getName();
    }
}
