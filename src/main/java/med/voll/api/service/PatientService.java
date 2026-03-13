package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.dto.Patient.PatientDetailDTO;
import med.voll.api.exception.PatientInactiveException;
import med.voll.api.exception.PatientNotFoundException;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import med.voll.api.dto.Patient.PatientRequest;
import med.voll.api.dto.Patient.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient registerPatient(PatientRequest request) {
        Patient patient = new Patient(request);

        patientRepository.save(patient);

        return patient;
    }

    public Page<PatientDTO> getActivePatients(Pageable pagination) {
        Page<Patient> patients = patientRepository.findPatientByActiveTrue(pagination);

        return patients.map(PatientDTO::new);
    }

    public Page<PatientDTO> getAllPatients(Pageable pagination) {
        Page<Patient> patients = patientRepository.findAll(pagination);

        return patients.map(PatientDTO::new);
    }

    public PatientDetailDTO getPatientDetail(Long id) {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Não existe paciente com esse id!"));

        return new PatientDetailDTO(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, UpdatePatientRequest request) throws Exception {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Não existe paciente com esse id!"));

        if(!patient.getActive()) {
            throw new PatientInactiveException("Esse paciente já foi excluído e não pode ser alterado!");
        }

        patient.updatePatient(request);

        patientRepository.save(patient);

        return patient;
    }

    @Transactional
    public Patient deletePatient(Long id) throws Exception {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Não existe paciente com esse id!"));

        if(!patient.getActive()) {
            throw new PatientInactiveException("Esse paciente já foi excluído!");
        }

        patient.inactivatePatient();

        return patient;
    }
}
