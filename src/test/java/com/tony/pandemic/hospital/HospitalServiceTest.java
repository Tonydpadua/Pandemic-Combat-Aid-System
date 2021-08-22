package com.tony.pandemic.hospital;

import com.tony.pandemic.exception.ObjectNotFoundException;
import com.tony.pandemic.item.ValidateItems;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static com.tony.pandemic.builders.HospitalBuilder.createHospital;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("service")
@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {

    @Mock
    private IHospitalRepository hospitalRepository;

    @Mock
    private ValidateItems validateItems;

    private HospitalServiceImpl hospitalService;

    @BeforeEach
    public void setUp() {
        this.hospitalService = new HospitalServiceImpl(hospitalRepository, validateItems);
    }

    @Test
    @DisplayName("save a hospital test")
    void shouldSaveHospital() throws ParseException {
        this.hospitalService.save(createHospital());

        ArgumentCaptor<Hospital> argumentCaptor = ArgumentCaptor.forClass(Hospital.class);
        verify(hospitalRepository).save(argumentCaptor.capture());

        Hospital hospitalResult = argumentCaptor.getValue();

        assertAll(
                "hospital",
                () -> assertThat(hospitalResult.getName().equals("Hospital Samaritano"))
        );
    }

    @Test
    @DisplayName("find hospital by successfully")
    void shouldReturnHospitalWithThisId() throws ParseException {
        when(this.hospitalRepository.findById(anyLong())).thenReturn(
                Optional.of(createHospital()));

        Hospital hospitalResult = this.hospitalService.findById(1L);

        assertAll(
                "hospital",
                () -> assertThat(hospitalResult.getName().equals("Hospital Samaritano"))
        );
    }

    @Test
    @DisplayName("find hospital with except")
    void shouldThrowHospitalNotFoundException() {
        when(this.hospitalRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> this.hospitalService.findById(1L));
    }

    @Test
    @DisplayName("find all hospitals successfully")
    void shouldFindAllHospitals() throws ParseException {
        when(this.hospitalService.findAll()).thenReturn(
                List.of(createHospital(), createHospital()));

        List<Hospital> hospitalResult = this.hospitalService.findAll();

        assertAll(
                "hospital",
                () -> MatcherAssert.assertThat(hospitalResult.size(), is(2)),
                () -> assertThat(hospitalResult.get(0).getName().equals("Hospital Samaritano")),
                () -> assertThat(hospitalResult.get(1).getName().equals("Hospital Samaritano"))
        );
    }

    @Test
    @DisplayName("update a rebel location test successfully")
    void shouldUpdateHospitalOccupation() throws ParseException {
        Hospital hospital = new Hospital();
        hospital.setPercentageOfOccupation(80);

        when(this.hospitalRepository.findById(1L)).thenReturn(Optional.of(createHospital()));

        this.hospitalService.updateOccupation(hospital, 1L);

        ArgumentCaptor<Hospital> argumentCaptor = ArgumentCaptor.forClass(Hospital.class);
        verify(hospitalRepository).save(argumentCaptor.capture());

        Hospital hospitalResult = argumentCaptor.getValue();

        assertAll("hospital",
                () -> assertEquals(80, hospitalResult.getPercentageOfOccupation())
        );
    }
}
