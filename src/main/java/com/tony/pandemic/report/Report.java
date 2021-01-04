package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private String percentageHighOccupation;

    private String percentageLowOccupation;

    Map<String,String> mediaHospitalItems = new HashMap<String,String>();

}
