package com.tony.pandemic.negotiation.involved;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.item.Item;

import java.util.List;

public interface IInvolvedService {

    boolean validatePoints(List<Item> solicitorItems, List<Item> receptorItems, Hospital solicitor, Hospital recepto);

    List<Item> addItems(Hospital solicitor, List<Item> offer);

    List<Item> removeItems(Hospital solicitor, List<Item> offer);

    List<Item> validateScoreHospitals(Hospital solicitor, InvolvedHospitalDTO offer);
}
