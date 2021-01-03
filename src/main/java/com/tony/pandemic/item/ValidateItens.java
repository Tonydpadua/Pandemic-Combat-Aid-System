package com.tony.pandemic.item;

import com.tony.pandemic.util.Messages;
import org.springframework.stereotype.Service;

@Service
public class ValidateItens {

    public void addPoints(Item itens) {

        if (itens.getName().equalsIgnoreCase(Messages.ITEM_DOCTOR)) {
            itens.setValueItem(3 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase(Messages.ITEM_NURSE)) {
            itens.setValueItem(3 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase(Messages.ITEM_RESPIRATOR)) {
            itens.setValueItem(5 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase(Messages.ITEM_TOMOGRAPH)) {
            itens.setValueItem(12 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase(Messages.ITEM_AMBULANCE)) {
            itens.setValueItem(10 * itens.getAmount());
        }
    }
}
