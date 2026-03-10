package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.dto.Doctor.DoctorRequest;
import med.voll.api.dto.Doctor.UpdateDoctorRequest;
import med.voll.api.model.Doctor;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailDTO> registerDoctor(
            @RequestBody @Valid DoctorRequest request, UriComponentsBuilder uriBuilder
    ) {
        Doctor doctor = doctorService.registerDoctor(request);

        var uri = uriBuilder.path("/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> getActiveDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        Page<DoctorDTO> pageDoctorDTO = doctorService.getActiveDoctors(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(pageDoctorDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        Page<DoctorDTO> pageDoctorDTO = doctorService.getAllDoctors(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(pageDoctorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> getDoctorDetail(@PathVariable Long id) {
        DoctorDetailDTO doctorDetailDTO = doctorService.getDoctorDetail(id);

        return ResponseEntity.status(HttpStatus.OK).body(doctorDetailDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorDetailDTO> updateDoctor(
            @PathVariable Long id, @RequestBody UpdateDoctorRequest request) throws Exception
    {
        Doctor doctor = doctorService.updateDoctor(id, request);

        return ResponseEntity.ok(new DoctorDetailDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) throws Exception {
        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }
}
