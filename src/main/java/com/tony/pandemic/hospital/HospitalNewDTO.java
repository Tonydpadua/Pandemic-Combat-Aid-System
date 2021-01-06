package com.tony.pandemic.hospital;

import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Name is mandatory")
    @Length(min=5,max=100,message="The length must be between 5 and 100 characters")
    private String name;

    @NotEmpty(message = "Address is mandatory")
    private String address;

    @NotEmpty(message = "Cnpj is mandatory")
    private String cnpj;

    @NotNull
    private double percentageOfOccupation;

    private LocalDateTime registrationTime;

    private Localization localization;

    private Resource resource;

}
