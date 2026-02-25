package med.voll.api.service;

import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import med.voll.api.request.Patient.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public void registerPatient(PatientRequest request) {
        Patient patient = new Patient(request);

        patientRepository.save(patient);
    }
}
