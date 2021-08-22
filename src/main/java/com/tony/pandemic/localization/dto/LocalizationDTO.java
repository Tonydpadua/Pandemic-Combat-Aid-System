package com.tony.pandemic.localization.dto;

import com.tony.pandemic.localization.Localization;
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
public class LocalizationDTO {


    private Long id;

    private String latitude;

    private String longitude;

    public static LocalizationDTO from(Localization localization) {
        return LocalizationDTO.builder()
                .id(localization.getId())
                .latitude(localization.getLatitude())
                .longitude(localization.getLongitude())
                .build();
    }
}
