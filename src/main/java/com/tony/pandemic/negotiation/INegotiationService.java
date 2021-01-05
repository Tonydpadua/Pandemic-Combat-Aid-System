package com.tony.pandemic.negotiation;

import com.tony.pandemic.negotiation.involved.InvolvedHospitalDTO;

public interface INegotiationService {

    void negotiationHospitals(InvolvedHospitalDTO solicitorHospital, InvolvedHospitalDTO receptorHospital);
}
