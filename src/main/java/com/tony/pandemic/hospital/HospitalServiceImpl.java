package com.tony.pandemic.hospital;

import com.tony.pandemic.exception.ObjectNotFoundException;
import com.tony.pandemic.item.ValidateItems;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements IHospitalService {


    private final IHospitalRepository hospitalRepository;

    private final ValidateItems validateItems;

    @Override
    public List<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    @Override
    public Hospital findById(Long id) {
        return this.hospitalRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException
                ("Hospital not found! Id: " + id));
    }

    @Transactional
    @Override
    public Hospital save(Hospital hospital) {
        hospital.getResource().getItems().forEach(this.validateItems::addPoints);

        hospital.getResource().setItems(hospital.getResource().getItems());
        hospital.setRegistrationTime(LocalDateTime.now());
        return this.hospitalRepository.save(hospital);
    }

    @Override
    public void updateOccupation(Hospital hospital, Long id) {
        this.findById(id);
        this.hospitalRepository.updateOccupation(hospital.getPercentageOfOccupation(), id);
    }

    @Override
    public Page<Hospital> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return this.hospitalRepository.findAll(PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }
}

