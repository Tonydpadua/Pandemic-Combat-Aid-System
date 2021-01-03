package com.tony.pandemic.negotiation;

import com.tony.pandemic.negotiation.involved.InvolvedHospital;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping(value = "hospitals/negotiation")
public class NegotiationRestController {

    private INegotiationService service;

    @PostMapping
    public void negotiationHospitals(@RequestBody InvolvedHospital[] involvedHospitals) {
        this.service.negotiationHospitals(involvedHospitals[0],involvedHospitals[1]);
    }
}
