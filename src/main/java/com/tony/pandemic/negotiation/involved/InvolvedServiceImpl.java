package com.tony.pandemic.negotiation.involved;

import com.tony.pandemic.exception.EmptyException;
import com.tony.pandemic.exception.InvalidNegotiationException;
import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.item.Item;
import com.tony.pandemic.item.ValidateItems;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class InvolvedServiceImpl implements IInvolvedService {

    private ValidateItems validateitems;

    @Override
    public boolean validatePoints(List<Item> solicitorItems, List<Item> receptorItems, Hospital solicitor, Hospital receptor) {
        int solicitorPoints = 0, receptorPoints = 0;
        for (Item items : solicitorItems) {
            solicitorPoints += (items.getValueItem() * items.getAmount() / 4);
        }
        for (Item items : receptorItems) {
            receptorPoints += (items.getValueItem() * items.getAmount());
        }
        if (solicitor.getPercentageOfOccupation() > 90.0) {
            if (solicitorPoints <= receptorPoints && receptor.getPercentageOfOccupation() < 90.0) {
                return true;
            }
        }
        if (receptor.getPercentageOfOccupation() > 90.0){
            if (solicitorPoints >= receptorPoints && solicitor.getPercentageOfOccupation() < 90.0) {
                return true;
            }
        }
        if (solicitorPoints != receptorPoints) {
            throw new InvalidNegotiationException("It is not possible to trade for invalid points");
        }
        return true;
    }

    @Override
    public List<Item> addItems(Hospital solicitor, List<Item> offerItems) {
        List<Item> solicitorItems = solicitor.getResource().getItems();
        for (int i = 0; i < solicitorItems.size(); i++) {
            for (int j = 0; j < offerItems.size(); j++) {
                if (solicitorItems.get(i).getName().equals(offerItems.get(j).getName())) {
                    solicitorItems.get(i).setAmount(solicitorItems.get(i).getAmount() + offerItems.get(j).getAmount());
                }
            }
        }
        return solicitorItems;
    }

    @Override
    public List<Item> removeItems(Hospital solicitor, List<Item> offer) {
        List<Item> solicitorItems = solicitor.getResource().getItems();
        for (int i = 0; i < solicitorItems.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                if (solicitorItems.get(i).getId() == offer.get(j).getId()) {
                    solicitorItems.get(i).setAmount(solicitorItems.get(i).getAmount() - offer.get(j).getAmount());
                }
            }
        }
        return solicitorItems;
    }

    @Override
    public List<Item> validateScoreHospitals(Hospital solicitor, InvolvedHospitalDTO offer) {
        List<Item> solicitorItems = solicitor.getResource().getItems();
        List<Item> offerItems = offer.getItems();

        if (solicitorItems.isEmpty() || offerItems.isEmpty()) {
            throw new EmptyException("It is not possible to trade for lack of items");
        }
        List<Item> finalitems = new ArrayList<>();
        for (int i = 0; i < solicitorItems.size(); i++) {
            for (int j = 0; j < offerItems.size(); j++) {
                String name = solicitorItems.get(i).getName();
                int amount = solicitorItems.get(i).getAmount();
                if (offerItems.get(j).getName().equals(name)) {
                    if (offerItems.get(j).getAmount() > amount) {
                        return finalitems;
                    } else {
                        Item item = offerItems.get(j);
                        item.setId(solicitorItems.get(i).getId());
                        this.validateitems.addPoints(item);
                        finalitems.add(item);
                    }
                } else {
                    finalitems.add(null);
                }
            }
        }
        finalitems.removeIf(n -> (n == null));
        if (finalitems.size() < offerItems.size()) {
            throw new InvalidNegotiationException("The total number of items does not remain the same");
        }
        return finalitems;
    }
}
