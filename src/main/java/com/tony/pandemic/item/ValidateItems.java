package com.tony.pandemic.item;

import com.tony.pandemic.util.Messages;
import org.springframework.stereotype.Service;

@Service
public class ValidateItems {

    public void addPoints(Item items) {

        if (items.getName().equalsIgnoreCase(Messages.ITEM_DOCTOR)) {
            items.setValueItem(3 * items.getAmount());
        } else if (items.getName().equalsIgnoreCase(Messages.ITEM_NURSE)) {
            items.setValueItem(3 * items.getAmount());
        } else if (items.getName().equalsIgnoreCase(Messages.ITEM_RESPIRATOR)) {
            items.setValueItem(5 * items.getAmount());
        } else if (items.getName().equalsIgnoreCase(Messages.ITEM_TOMOGRAPH)) {
            items.setValueItem(12 * items.getAmount());
        } else if (items.getName().equalsIgnoreCase(Messages.ITEM_AMBULANCE)) {
            items.setValueItem(10 * items.getAmount());
        }
    }
}
