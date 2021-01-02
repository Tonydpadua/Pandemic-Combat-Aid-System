package com.tony.pandemic.hospital;

import javax.transaction.Transactional;

public interface IHospitalService {

    @Transactional
    Hospital save(Hospital hospital);

    Hospital fromDTO(HospitalNewDTO objNewDTO);

    Hospital findById(Long id);

   void updateOccupation(Hospital hospital, Long id);
}
