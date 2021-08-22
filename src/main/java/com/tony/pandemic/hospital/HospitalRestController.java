package com.tony.pandemic.hospital;

import com.tony.pandemic.hospital.dto.HospitalDTO;
import com.tony.pandemic.hospital.dto.HospitalDetailedDTO;
import com.tony.pandemic.hospital.dto.HospitalNewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hospitals")
public class HospitalRestController {


    private final IHospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<HospitalDTO>> findAll() {
        return ResponseEntity.ok().body(this.hospitalService.findAll().stream().map(HospitalDTO::from).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalDetailedDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(HospitalDetailedDTO.from(this.hospitalService.findById(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@Valid @RequestBody HospitalNewDTO hospitalNewDTO) {
        this.hospitalService.save(Hospital.from(hospitalNewDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/occupation/{id}")
    public void updateOccupation(@PathVariable Long id, @RequestBody HospitalDTO hospitalDTO) {
        this.hospitalService.updateOccupation(Hospital.from(hospitalDTO), id);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<HospitalDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(this.hospitalService.findPage(page, linesPerPage, orderBy, direction).map(HospitalDTO::from));
    }
}
