package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalService;
import com.tony.pandemic.util.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListItemsImpl implements IListItems{

    private IHospitalService hospitalService;
    private DecimalFormat formatter = new DecimalFormat("0.00");

    public ListItemsImpl(IHospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public Map<String, String> listItens() {
        List<Hospital> hospitals = this.hospitalService.findAll();

        Map<String, String> list = new HashMap<String, String>();

        if ( hospitals.size() == 0 ){
            return list;
        }

        double totalHospitals = hospitals.size();
        int doctorAmount = 0;
        int nurseAmount = 0;
        int respiratorAmount = 0;
        int tomographAmount = 0;
        int ambulanceAmount = 0;

        for (Hospital hospital : hospitals) {
            for (int j = 0; j < hospital.getResource().getItems().size(); j++) {
                if (Messages.ITEM_DOCTOR.equals(hospital.getResource().getItems().get(j).getName())) {
                    doctorAmount++;
                }
                if (Messages.ITEM_NURSE.equals(hospital.getResource().getItems().get(j).getName())) {
                    nurseAmount++;
                }
                if (Messages.ITEM_RESPIRATOR.equals(hospital.getResource().getItems().get(j).getName())) {
                    respiratorAmount++;
                }
                if (Messages.ITEM_TOMOGRAPH.equals(hospital.getResource().getItems().get(j).getName())) {
                    tomographAmount++;
                }
                if (Messages.ITEM_AMBULANCE.equals(hospital.getResource().getItems().get(j).getName())) {
                    ambulanceAmount++;
                }
            }
        }

        if (doctorAmount == 0) {
            list.put(Messages.ITEM_DOCTOR, "0.00");
        } else {
            double valor = doctorAmount / totalHospitals;
            String result = this.formatter.format(valor);
            list.put(Messages.ITEM_DOCTOR, result);
        }
        if (nurseAmount == 0) {
            list.put(Messages.ITEM_NURSE, "0.00");
        } else {
            double valor = nurseAmount / totalHospitals;
            String result = this.formatter.format(valor);
            list.put(Messages.ITEM_NURSE, result);
        }
        if (respiratorAmount == 0) {
            list.put(Messages.ITEM_RESPIRATOR, "0.00");
        } else {
            double valor = respiratorAmount / totalHospitals;
            String result = this.formatter.format(valor);
            list.put(Messages.ITEM_RESPIRATOR, result);
        }
        if (tomographAmount == 0) {
            list.put(Messages.ITEM_TOMOGRAPH, "0.00");
        } else {
            double valor = tomographAmount / totalHospitals;
            String result = this.formatter.format(valor);
            list.put(Messages.ITEM_TOMOGRAPH, result);
        }
        if (ambulanceAmount == 0) {
            list.put(Messages.ITEM_AMBULANCE, "0.00");
        } else {
            double valor = ambulanceAmount / totalHospitals;
            String result = this.formatter.format(valor);
            list.put(Messages.ITEM_AMBULANCE, result);
        }
        return list;
    }
}
