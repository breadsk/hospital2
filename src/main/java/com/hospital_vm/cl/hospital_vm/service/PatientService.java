package com.hospital_vm.cl.hospital_vm.service;

import com.hospital_vm.cl.hospital_vm.model.Patient;
import com.hospital_vm.cl.hospital_vm.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
