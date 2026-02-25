package med.voll.api.service;

import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import med.voll.api.request.Patient.PatientRequest;
import med.voll.api.request.Patient.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void updatePatient(Long id, UpdatePatientRequest request) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isEmpty()) {
            throw new Exception("Não existe paciente com esse id!");
        }

        Patient patient = patientOptional.get();

        patient.updatePatient(request);

        patientRepository.save(patient);
    }

    public void deletePatient(Long id) throws Exception {
        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isEmpty()) {
            throw new Exception("Não existe paciente com esse id!");
        }

        patientRepository.deleteById(id);
    }
}
