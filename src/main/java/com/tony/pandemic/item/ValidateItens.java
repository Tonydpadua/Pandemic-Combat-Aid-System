package com.tony.pandemic.item;

import org.springframework.stereotype.Service;

@Service
public class ValidateItens {

    public void addPoints(Item itens) {

        if (itens.getName().equalsIgnoreCase("Doctor")) {
            itens.setValueItem(3 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase("Nurse")) {
            itens.setValueItem(3 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase("Respirator")) {
            itens.setValueItem(5 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase("Tomograph")) {
            itens.setValueItem(12 * itens.getAmount());
        } else if (itens.getName().equalsIgnoreCase("Ambulance")) {
            itens.setValueItem(10 * itens.getAmount());
        }
    }
}
