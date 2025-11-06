package com.hospital_vm.cl.hospital_vm;

import com.hospital_vm.cl.hospital_vm.model.Patient;
import com.hospital_vm.cl.hospital_vm.repository.PatientRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class PatientDataLoader implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        // Random random = new Random();

        // Generar pacientes
        for (int i = 0; i < 3; i++) {
            Patient patient = new Patient();
            // Le quito el id porque no estoy actualizando
            // estoy guardando
            // patient.setId((long) (i + 1)); // Cast explicito a long
            patient.setRut((18000000 + i) + "-" + faker.number().numberBetween(0, 9));
            patient.setFirstName(faker.artist().name());
            patient.setLastName(faker.futurama().character());
            patient.setBirthDate(LocalDate.of(1983, 9, 13));
            patient.setEmail(faker.internet().emailAddress());
            patientRepository.save(patient);
        }

    }

}
