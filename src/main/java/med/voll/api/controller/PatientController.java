package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Patient.PatientDTO;
import med.voll.api.dto.Patient.PatientDetailDTO;
import med.voll.api.dto.Patient.PatientRequest;
import med.voll.api.dto.Patient.UpdatePatientRequest;
import med.voll.api.model.Patient;
import med.voll.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailDTO> registerPatient(
            @RequestBody @Valid PatientRequest request, UriComponentsBuilder uriBuilder)
    {
        Patient patient = patientService.registerPatient(request);

        var uri = uriBuilder.path("patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getActivePatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        Page<PatientDTO> pagePatientDTO = patientService.getActivePatients(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(pagePatientDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PatientDTO>> getAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        Page<PatientDTO> pagePatientDTO = patientService.getAllPatients(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(pagePatientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailDTO> getPatientDetail(@PathVariable Long id){
        PatientDetailDTO patientDetailDTO = patientService.getPatientDetail(id);

        return ResponseEntity.status(HttpStatus.OK).body(patientDetailDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientDetailDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) throws Exception{
        Patient patient = patientService.updatePatient(id, request);

        return ResponseEntity.ok(new PatientDetailDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletePatient(@PathVariable Long id) throws Exception {
        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }
}
