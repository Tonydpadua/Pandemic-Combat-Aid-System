package com.tony.pandemic.hospital;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/hospitals")
public class HospitalRestController {

    private IHospitalService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody HospitalNewDTO hospitalNewDTO) {
        Hospital obj = this.service.fromDTO(hospitalNewDTO);
        obj = this.service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
