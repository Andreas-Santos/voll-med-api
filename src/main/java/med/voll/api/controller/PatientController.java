package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.dto.Patient.PatientRequest;
import med.voll.api.dto.Patient.UpdatePatientRequest;
import med.voll.api.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerPatient(@RequestBody @Valid PatientRequest request) {
        patientService.registerPatient(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente cadastrado com sucesso!");
    }

    @GetMapping
    public Page<PatientDTO> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientService.getPatients(pagination);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) throws Exception{
        patientService.updatePatient(id, request);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletePatient(@PathVariable Long id) throws Exception {
        patientService.deletePatient(id);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso!");
    }
}
