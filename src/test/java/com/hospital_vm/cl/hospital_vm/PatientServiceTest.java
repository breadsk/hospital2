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

    @Test
    public void testSave() {
        Patient patient = new Patient(1L, // Long, no int
                "12345678-9", // RUT válido
                "Nicolas",
                "Caceres",
                LocalDate.of(1983, 9, 13), // LocalDate, no String
                "nicolas.programador@gmail.com");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient saved = patientService.save(patient);
        assertNotNull(saved);
        assertEquals("nicolas.programador@gmail.com", saved.getEmail());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(patientRepository).deleteById(id);

        patientService.delete(id);
        verify(patientRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByEmail() {
        String email = "nicolas.programador@gmail.com";
        Patient patient = new Patient(1L, // Long, no int
                "12345678-9", // RUT válido
                "Nicolas",
                "Caceres",
                LocalDate.of(1983, 9, 13), // LocalDate, no String
                "nicolas.programador@gmail.com");

        when(patientRepository.findByEmail(email)).thenReturn(patient);

        // Llama al método findByCodigo() del servicio.
        Patient found = patientService.findByEmail(email);

        // Verifica que la Carrera devuelta no sea nula y que su código coincida con el
        // código esperado.
        assertNotNull(found);
        assertEquals(email, found.getEmail());// Este es el que comprueba que me retorne lo mismo
        // Que le envie en mi mock
    }
}
