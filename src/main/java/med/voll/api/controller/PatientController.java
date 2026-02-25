package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.request.Patient.PatientRequest;
import med.voll.api.request.Patient.UpdatePatientRequest;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<PatientDTO> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientService.getPatients(pagination);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) throws Exception{
        patientService.updatePatient(id, request);
    }
}
