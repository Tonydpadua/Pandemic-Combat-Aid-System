package com.tony.pandemic.hospital;

import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String address;

    private String cnpj;

    private double percentageOfOccupation;

    private Localization localization;

    private Resource resource;

}
