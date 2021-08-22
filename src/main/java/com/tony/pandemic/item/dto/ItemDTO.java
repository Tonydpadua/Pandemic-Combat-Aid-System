package com.tony.pandemic.item.dto;

import com.tony.pandemic.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {


    private Long id;

    private String name;

    private int amount;

    private int valueItem;

    public static ItemDTO from(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .amount(item.getAmount())
                .valueItem(item.getValueItem())
                .build();
    }
}
