package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private String percentageHighOccupation;

    private String percentageLowOccupation;

    private Map<String, String> mediaHospitalItems = new HashMap<String, String>();

    private String highOccupationMostTime;

    private String lowOccupationMostTime;

    private List<String> negotiationHistory;

}
