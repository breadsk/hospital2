package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.dto.ApiResponse;
import com.hospital_vm.cl.hospital_vm.model.Patient;
import com.hospital_vm.cl.hospital_vm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> list() {
        List<Patient> patients = patientService.findAll();

        if (patients.isEmpty()) {
            ApiResponse<List<Patient>> response = new ApiResponse<>(
                    false,
                    HttpStatus.NO_CONTENT.value(),
                    "No se encontraron pacientes en el sistema",
                    null,
                    0L);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        ApiResponse<List<Patient>> response = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Pacientes obtenidos satisfactoriamente",
                patients,
                (long) patients.size());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> save(@RequestBody Patient patient) {

        try {
            Patient newPatient = patientService.save(patient);

            ApiResponse<Patient> response = new ApiResponse<>(
                    true,
                    HttpStatus.CREATED.value(),
                    "Paciente creado exitosamente",
                    newPatient,
                    1L);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();

            ApiResponse<Patient> response = new ApiResponse<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + e.getMessage(),
                    null,
                    0L);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> searchById(@PathVariable Long id) { // ← Cambia a Long
        try {
            Patient patient = patientService.findById(id);
            ApiResponse<Patient> response = new ApiResponse<>(
                    true,
                    HttpStatus.OK.value(),
                    "Paciente encontrado",
                    patient,
                    1L);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ApiResponse<Patient> response = new ApiResponse<>(
                    false,
                    HttpStatus.NOT_FOUND.value(),
                    "Paciente no encontrado",
                    null,
                    0L);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try {
            // verificar si existe
            Patient patient = patientService.findById(id);

            System.out.println("Paciente encontrado: "
                    + patient.getFirstName() + " "
                    + patient.getLastName());

            // Luego lo elimina
            patientService.delete(id);
            ApiResponse<String> response = new ApiResponse<>(
                    true,
                    HttpStatus.OK.value(),
                    "Paciente eliminado satisfactoriamente",
                    "Paciente con ID: " + id + " fue eliminado",
                    1L);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            System.out.println("Error eliminando paciente: " + ex.getMessage());
            ApiResponse<String> response = new ApiResponse<>(
                    false,
                    HttpStatus.NOT_FOUND.value(),
                    "Paciente no encontrado",
                    "No se encontró paciente con ese id",
                    0L);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> update(@PathVariable Long id, @RequestBody Patient patientData) {

        try {
            // 1. Buscar paciente existente en la BD
            Patient existingPatient = patientService.findById(id);

            // 2. Actualizar los campos del paciente existente con los nuevos datos
            existingPatient.setRut(patientData.getRut());
            existingPatient.setFirstName(patientData.getFirstName());
            existingPatient.setLastName(patientData.getLastName());
            existingPatient.setBirthDate(patientData.getBirthDate());
            existingPatient.setEmail(patientData.getEmail());

            // 3. Guardar (actualizar) el paciente existente
            Patient updatedPatient = patientService.save(existingPatient);

            ApiResponse<Patient> response = new ApiResponse<>(
                    true,
                    HttpStatus.OK.value(),
                    "Paciente actualizado satisfactoriamente",
                    updatedPatient,
                    1L);
            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            System.out.println("Error actualizando paciente: " + ex.getMessage());
            ApiResponse<Patient> response = new ApiResponse<>(
                    false,
                    HttpStatus.NOT_FOUND.value(),
                    "Paciente no encontrado",
                    null,
                    0L);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}