package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PercentualHospital implements IPercentualHospital{

    private IHospitalService hospitalService;
    DecimalFormat formatter = new DecimalFormat("0.00");

    public PercentualHospital(IHospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public String percentualHighOccupation() {
        List<Hospital> hospitalsHighOccupation = this.hospitalService.findAll().stream().filter
                (hospital -> hospital.getPercentageOfOccupation() >= 90.0).collect(Collectors.toList());
        int totalHospitals = this.hospitalService.findAll().size();
        if (totalHospitals == 0)
            return "0.00 %";
        double percent = (hospitalsHighOccupation.size() * 100) / totalHospitals;
        String result =this.formatter.format(percent);
        return result + " %";
    }

    @Override
    public String percentualLowOccupation() {
        List<Hospital> hospitalsLowOccupation = this.hospitalService.findAll().stream().filter
                (hospital -> hospital.getPercentageOfOccupation() < 90.0).collect(Collectors.toList());
        int totalHospitals = this.hospitalService.findAll().size();
        if (totalHospitals == 0)
            return "0.00 %";
        double percent = (hospitalsLowOccupation.size() * 100) / totalHospitals;
        String result =this.formatter.format(percent);
        return result + " %";
    }
}
