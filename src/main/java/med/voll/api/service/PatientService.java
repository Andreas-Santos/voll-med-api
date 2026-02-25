package med.voll.api.service;

import med.voll.api.dto.Patient.PatientDetailDTO;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import med.voll.api.request.Patient.PatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PatientDetailDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientDetailDTO> patientsDTO = patients
                .stream().map(PatientDetailDTO::new).toList();

        return patientsDTO;
    }
}
