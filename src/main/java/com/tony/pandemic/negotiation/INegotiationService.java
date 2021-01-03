package com.tony.pandemic.negotiation;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.item.Item;
import com.tony.pandemic.negotiation.involved.InvolvedHospital;

import java.util.List;

public interface INegotiationService {

    void negotiationHospitals(InvolvedHospital solicitorHospital, InvolvedHospital receptorHospital);
}
