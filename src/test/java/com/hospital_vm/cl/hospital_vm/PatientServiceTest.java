package com.hospital_vm.cl.hospital_vm;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hospital_vm.cl.hospital_vm.model.Patient;
import com.hospital_vm.cl.hospital_vm.repository.PatientRepository;
import com.hospital_vm.cl.hospital_vm.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testFindAll() {
        when(patientRepository.findAll())
                .thenReturn(
                        List.of(
                                new Patient(
                                        1L, // Long, no int
                                        "12345678-9", // RUT válido
                                        "Nicolas",
                                        "Caceres",
                                        LocalDate.of(1983, 9, 13), // LocalDate, no String
                                        "nicolas.programador@gmail.com")));
        List<Patient> patients = patientService.findAll();
        assertNotNull(patients);
        assertEquals(1, patients.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Patient patient = new Patient(1L, // Long, no int
                "12345678-9", // RUT válido
                "Nicolas",
                "Caceres",
                LocalDate.of(1983, 9, 13), // LocalDate, no String
                "nicolas.programador@gmail.com");
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Patient found = patientService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

}
