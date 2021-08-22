package com.tony.pandemic.localization;

import com.tony.pandemic.localization.dto.LocalizationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Localization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latitude;

    private String longitude;

    public static Localization from(LocalizationDTO localizationDTO) {
        return Localization.builder()
                .id(localizationDTO.getId())
                .latitude(localizationDTO.getLatitude())
                .longitude(localizationDTO.getLongitude())
                .build();
    }
}
