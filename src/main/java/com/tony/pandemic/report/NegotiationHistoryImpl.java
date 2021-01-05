package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NegotiationHistoryImpl implements INegotiationHistory{

    List<String> list = new ArrayList<>();

    @Override
    public List<String> saveNegotiationHistory(Hospital solicitor, Hospital receptor) {
        this.list.add("Trade has been made between " + solicitor.getName() + " and " + receptor.getName());
        return this.list;
    }

    @Override
    public List<String> makeNegotiationHistory() {
        return this.list;
    }


}
