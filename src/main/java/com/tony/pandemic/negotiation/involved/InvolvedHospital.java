package com.tony.pandemic.negotiation.involved;

import com.tony.pandemic.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvolvedHospital {

    @NotBlank
    private Long idHospital;
    @NotBlank
    private List<Item> items;
}
