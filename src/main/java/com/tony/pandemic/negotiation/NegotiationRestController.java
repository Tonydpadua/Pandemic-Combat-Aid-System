package com.tony.pandemic.negotiation;

import com.tony.pandemic.negotiation.involved.InvolvedHospitalDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Controller
@RequestMapping(value = "hospitals/negotiation")
public class NegotiationRestController {

    private INegotiationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void negotiationHospitals(@RequestBody InvolvedHospitalDTO[] involvedHospitals) {
        this.service.negotiationHospitals(involvedHospitals[0], involvedHospitals[1]);
    }
}
