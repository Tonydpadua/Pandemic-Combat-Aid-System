package com.tony.pandemic.hospital;

import com.tony.pandemic.exception.ObjectNotFoundException;
import com.tony.pandemic.item.Item;
import com.tony.pandemic.item.ValidateItens;
import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HospitalServiceImpl implements IHospitalService {

    private IHospitalRepository repository;
    private final ValidateItens validateItens;

    @Override
    public List<Hospital> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Hospital findById(Long id) {
        Optional<Hospital> hospital = this.repository.findById(id);
        return hospital.orElseThrow(() -> new ObjectNotFoundException
                ("Object not found! Id: " + id + ", Type: " + Hospital.class.getName()));
    }

    @Override
    public Hospital save(Hospital hospital) {
        List<Item> itens = hospital.getResource().getItens();

        for (int i = 0; i < itens.size(); i++) {
            this.validateItens.addPoints(itens.get(i));
        }
        hospital.getResource().setItens(itens);
        return this.repository.save(hospital);
    }

    @Override
    public void updateOccupation(Hospital hospital, Long id) {
        this.findById(id);
        this.repository.updateOccupation(hospital.getPercentageOfOccupation(), id);
    }

    @Override
    public Hospital fromDTO(HospitalNewDTO objNewDTO) {
        Localization localization = new Localization(null, objNewDTO.getLocalization()
                .getLatitude(), objNewDTO.getLocalization().getLongitude());
        Resource resource = new Resource(null, objNewDTO.getResource().getItens());
        Hospital hospital = new Hospital(null, objNewDTO.getName(), objNewDTO.getAddress(), objNewDTO.getCnpj(),
                objNewDTO.getPercentageOfOccupation(), localization, resource);
        return hospital;
    }

}

