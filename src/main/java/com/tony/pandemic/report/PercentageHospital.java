package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PercentageHospital implements IPercentageHospital {

    private final IHospitalService hospitalService;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public String percentageHighOccupation() {
        List<Hospital> hospitalsHighOccupation = this.hospitalService.findAll().stream().filter
                (hospital -> hospital.getPercentageOfOccupation() >= 90.0).collect(Collectors.toList());
        return getString(hospitalsHighOccupation);
    }

    @Override
    public String percentageLowOccupation() {
        List<Hospital> hospitalsLowOccupation = this.hospitalService.findAll().stream().filter
                (hospital -> hospital.getPercentageOfOccupation() < 90.0).collect(Collectors.toList());
        return getString(hospitalsLowOccupation);
    }

    private String getString(List<Hospital> hospitals) {
        int totalHospitals = this.hospitalService.findAll().size();
        if (totalHospitals == 0)
            return "0.00 %";
        double percent = (hospitals.size() * 100) / totalHospitals;
        String result =this.decimalFormat.format(percent);
        return result + " %";
    }
}
