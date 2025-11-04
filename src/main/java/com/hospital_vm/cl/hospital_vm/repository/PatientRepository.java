package com.hospital_vm.cl.hospital_vm.repository;

import com.hospital_vm.cl.hospital_vm.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Encuentra pacientes por apellidos
    // Consultas basadas en convenciones
    List<Patient> findByLastName(String lastName);

    // Encuentra pacientes por correo electronico
    Patient findByEmail(String email);

    // Encuentra pacientes por nombre y apellido
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
