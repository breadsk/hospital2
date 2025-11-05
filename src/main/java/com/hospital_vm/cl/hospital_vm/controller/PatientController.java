package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.dto.ApiResponse;
import com.hospital_vm.cl.hospital_vm.model.Patient;
import com.hospital_vm.cl.hospital_vm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> listar() {
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

}
