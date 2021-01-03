package com.tony.pandemic.negotiation;

import com.tony.pandemic.exception.EmptyException;
import com.tony.pandemic.exception.InvalidNegotiationException;
import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalRepository;
import com.tony.pandemic.hospital.IHospitalService;
import com.tony.pandemic.item.Item;
import com.tony.pandemic.negotiation.involved.IInvolvedService;
import com.tony.pandemic.negotiation.involved.InvolvedHospital;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NegotiationService implements INegotiationService {

    private IInvolvedService involvedService;
    private IHospitalRepository hospitalRepository;
    private IHospitalService hospitalService;

    @Override
    public void negotiationHospitals(InvolvedHospital solicitorHospital, InvolvedHospital receptorHospital) {

        Hospital solicitor = this.hospitalService.findById(solicitorHospital.getIdHospital());
        Hospital receptor = this.hospitalService.findById(receptorHospital.getIdHospital());

        List<Item> ItemsSolicitor = this.involvedService.validateScoreHospitals
                (solicitor.getResource().getItems(), solicitorHospital.getItems());
        List<Item> ItemsReceptor = this.involvedService.validateScoreHospitals
                (receptor.getResource().getItems(), receptorHospital.getItems());

        if (ItemsSolicitor.isEmpty() || ItemsReceptor.isEmpty()) {
            throw new EmptyException("It is not possible to exchange for lack of items");
        } else {
            if (!this.involvedService.validatePoints(ItemsSolicitor, ItemsReceptor)) {
                throw new InvalidNegotiationException("It is not possible to exchange");
            } else {
                solicitor.getResource()
                        .setItems(this.involvedService.addItems(solicitor.getResource().getItems(),
                                ItemsReceptor));

                solicitor.getResource()
                        .setItems(this.involvedService.removeItems(solicitor.getResource().getItems(),
                                ItemsSolicitor));

                receptor.getResource()
                        .setItems(this.involvedService.addItems(receptor.getResource().getItems(),
                                ItemsSolicitor));

                receptor.getResource()
                        .setItems(this.involvedService.removeItems(receptor.getResource().getItems(),
                                ItemsReceptor));

                this.hospitalRepository.save(solicitor);
                this.hospitalRepository.save(receptor);
            }
        }
    }
}
