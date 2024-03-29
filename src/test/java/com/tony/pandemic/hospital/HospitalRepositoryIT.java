package com.tony.pandemic.hospital;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class HospitalRepositoryIT {

    @Autowired
    private IHospitalRepository repository;

    Hospital hospital;

    @Test
    public void findAll() {
        this.createHospital();
        Assertions.assertNotNull(this.repository.findAll());
    }

    @Test
    public void findById() {
        this.createHospital();
        List<Hospital> hospitals = this.repository.findAll();
        assertThat(hospitals.size()).isEqualTo(1);
    }

    @Test
    public void save() {
        this.createHospital();
        assertThat(hospital.getName()).isEqualTo("Hospital Samaritano");
    }

    private void createHospital() {
        this.hospital = new Hospital( "Hospital Samaritano", "Avenida Santa Rita", "532453", 50.0);
        this.repository.save(hospital);
    }
}
