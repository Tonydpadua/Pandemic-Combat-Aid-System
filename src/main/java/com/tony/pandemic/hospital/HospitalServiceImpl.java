package com.tony.pandemic.hospital;

import com.tony.pandemic.item.Item;
import com.tony.pandemic.item.ValidateItens;
import com.tony.pandemic.localization.Localization;
import com.tony.pandemic.resource.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HospitalServiceImpl implements IHospitalService{

    private IHospitalRepository repository;
    private final ValidateItens validateItens;

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
    public Hospital fromDTO(HospitalNewDTO objNewDTO) {
        Localization localization = new Localization(null, objNewDTO.getLocalization()
                .getLatitude(), objNewDTO.getLocalization().getLongitude());
        Resource resource = new Resource(null, objNewDTO.getResource().getItens());
        Hospital hospital = new Hospital(null, objNewDTO.getName(), objNewDTO.getAddress(), objNewDTO.getCnpj(),
                objNewDTO.getPercentageOfOccupation(), localization, resource);
        return hospital;
    }

}
