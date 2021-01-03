package com.tony.pandemic.hospital;

import java.util.List;

public interface IHospitalService {

    Hospital save(Hospital hospital);

    Hospital fromDTO(HospitalDTO objDTO);

    Hospital fromDTO(HospitalNewDTO objNewDTO);

    Hospital findById(Long id);

   void updateOccupation(Hospital hospital, Long id);

   List<Hospital> findAll();
}
