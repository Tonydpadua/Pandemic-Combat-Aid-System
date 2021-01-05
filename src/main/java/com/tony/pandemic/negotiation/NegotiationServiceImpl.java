package com.tony.pandemic.negotiation;

import com.tony.pandemic.exception.EmptyException;
import com.tony.pandemic.exception.InvalidNegotiationException;
import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalRepository;
import com.tony.pandemic.hospital.IHospitalService;
import com.tony.pandemic.item.Item;
import com.tony.pandemic.item.ValidateItems;
import com.tony.pandemic.negotiation.involved.IInvolvedService;
import com.tony.pandemic.negotiation.involved.InvolvedHospital;
import com.tony.pandemic.report.INegotiationHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NegotiationServiceImpl implements INegotiationService {

    private IInvolvedService involvedService;
    private IHospitalRepository hospitalRepository;
    private IHospitalService hospitalService;
    private INegotiationHistory negotiationHistory;
    private ValidateItems validateItems;

    @Override
    public void negotiationHospitals(InvolvedHospital solicitorHospital, InvolvedHospital receptorHospital) {

        Hospital solicitor = this.hospitalService.findById(solicitorHospital.getIdHospital());
        Hospital receptor = this.hospitalService.findById(receptorHospital.getIdHospital());

        List<Item> itemsSolicitor = this.involvedService.validateScoreHospitals
                (solicitor.getResource().getItems(), solicitorHospital.getItems());
        List<Item> itemsReceptor = this.involvedService.validateScoreHospitals
                (receptor.getResource().getItems(), receptorHospital.getItems());

        if (itemsSolicitor.isEmpty() || itemsReceptor.isEmpty()) {
            throw new EmptyException("It is not possible to trade for lack of items");
        } else {
            if (!this.involvedService.validatePoints(itemsSolicitor, itemsReceptor, solicitor, receptor)) {
                throw new InvalidNegotiationException("It is not possible to trade for invalid points");
            } else {
                this.negotiationHistory.saveNegotiationHistory(solicitor, receptor);
                solicitor.getResource()
                        .setItems(this.involvedService.addItems(solicitor.getResource().getItems(),
                                itemsReceptor));

                solicitor.getResource()
                        .setItems(this.involvedService.removeItems(solicitor.getResource().getItems(),
                                itemsSolicitor));

                receptor.getResource()
                        .setItems(this.involvedService.addItems(receptor.getResource().getItems(),
                                itemsSolicitor));

                receptor.getResource()
                        .setItems(this.involvedService.removeItems(receptor.getResource().getItems(),
                                itemsReceptor));

                for (Item item : solicitor.getResource().getItems()) {
                    this.validateItems.addPoints(item);
                }
                for (Item item : receptor.getResource().getItems()) {
                    this.validateItems.addPoints(item);
                }
                this.hospitalRepository.save(solicitor);
                this.hospitalRepository.save(receptor);
            }
        }
    }
}
