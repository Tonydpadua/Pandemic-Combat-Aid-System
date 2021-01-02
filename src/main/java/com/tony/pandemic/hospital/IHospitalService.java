package com.tony.pandemic.hospital;

public interface IHospitalService {

    Hospital save(Hospital hospital);

    Hospital fromDTO(HospitalNewDTO objNewDTO);

}
