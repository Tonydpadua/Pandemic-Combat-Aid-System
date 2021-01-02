package com.tony.pandemic.hospital;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/hospitals")
public class HospitalRestController {

    private IHospitalService service;

    @GetMapping
    public ResponseEntity<List<HospitalDTO>> findAll() {
        List<Hospital> hospitals = this.service.findAll();
        List<HospitalDTO> listDTO = hospitals.stream().map(obj -> new HospitalDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody HospitalNewDTO hospitalNewDTO) {
        Hospital obj = this.service.fromDTO(hospitalNewDTO);
        obj = this.service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hospital> findById(@PathVariable Long id) {
        Hospital hospital = this.service.findById(id);
        return ResponseEntity.ok().body(hospital);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateOccupation(@PathVariable Long id, @RequestBody Hospital hospital) {
        this.service.updateOccupation(hospital, id);
        return ResponseEntity.noContent().build();
    }

}
