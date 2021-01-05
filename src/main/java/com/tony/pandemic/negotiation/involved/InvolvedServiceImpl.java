package com.tony.pandemic.negotiation.involved;

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
        if (solicitorPoints != receptorPoints)
            return false;
        return true;
    }

    @Override
    public List<Item> addItems(List<Item> solicitor, List<Item> offer) {
        for (int i = 0; i < solicitor.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                if (solicitor.get(i).getName().equals(offer.get(j).getName())) {
                    solicitor.get(i).setAmount(solicitor.get(i).getAmount() + offer.get(j).getAmount());
                }
            }
        }
        return solicitor;
    }

    @Override
    public List<Item> removeItems(List<Item> solicitor, List<Item> offer) {
        for (int i = 0; i < solicitor.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                if (solicitor.get(i).getId() == offer.get(j).getId()) {
                    solicitor.get(i).setAmount(solicitor.get(i).getAmount() - offer.get(j).getAmount());
                }
            }
        }
        return solicitor;
    }

    @Override
    public List<Item> validateScoreHospitals(List<Item> solicitor, List<Item> offer) {
        List<Item> finalitems = new ArrayList<>();
        for (int i = 0; i < solicitor.size(); i++) {
            for (int j = 0; j < offer.size(); j++) {
                String name = solicitor.get(i).getName();
                int amount = solicitor.get(i).getAmount();
                if (offer.get(j).getName().equals(name)) {
                    if (offer.get(j).getAmount() > amount) {
                        return finalitems;
                    } else {
                        Item item = offer.get(j);
                        item.setId(solicitor.get(i).getId());
                        this.validateitems.addPoints(item);
                        finalitems.add(item);
                    }
                } else {
                    finalitems.add(null);
                }
            }
        }
        finalitems.removeIf(n -> (n == null));
        if (finalitems.size() < offer.size()) {
            throw new InvalidNegotiationException("The total number of items does not remain the same");
        }
        return finalitems;
    }
}
