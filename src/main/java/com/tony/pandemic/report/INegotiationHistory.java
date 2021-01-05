package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;

import java.util.List;

public interface INegotiationHistory {

    List<String> saveNegotiationHistory(Hospital solicitor, Hospital receptor);

    List<String> makeNegotiationHistory();
}
