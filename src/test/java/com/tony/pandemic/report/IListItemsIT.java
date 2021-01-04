package com.tony.pandemic.report;

import com.tony.pandemic.hospital.Hospital;
import com.tony.pandemic.hospital.IHospitalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IListItemsIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IHospitalRepository repository;

    Hospital hospital;

    @Test
    @DisplayName("Sanity Integration Controller test")
    public void getReport() {
        this.createHospital();
        ResponseEntity<Report> response = restTemplate.getForEntity("http://localhost:8080/hospitals/reports", Report.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Report report = response.getBody();
        assertNotNull(report);
        assertNotNull(report.getLowOccupationMostTime());
    }

    private void createHospital() {
        this.hospital = new Hospital( "Hospital Samaritano", "Avenida Santa Rita", "532453", 50.0);
        this.repository.save(hospital);
    }

}
