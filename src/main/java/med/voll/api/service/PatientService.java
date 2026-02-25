package med.voll.api.service;

import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import med.voll.api.request.Patient.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public void registerPatient(PatientRequest request) {
        Patient patient = new Patient(request);

        patientRepository.save(patient);
    }

    public Page<PatientDTO> getPatients(Pageable pagination) {
        Page<Patient> patients = patientRepository.findAll(pagination);

        return patients.map(PatientDTO::new);
    }
}
