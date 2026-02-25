package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.dto.Patient.PatientDetailDTO;
import med.voll.api.request.Patient.PatientRequest;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    @Transactional
    public void registerPatient(@RequestBody @Valid PatientRequest request) {
        patientService.registerPatient(request);
    }

    @GetMapping
    public List<PatientDetailDTO> getPatients() {
        return patientService.getPatients();
    }
}
