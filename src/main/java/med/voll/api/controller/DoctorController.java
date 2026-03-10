package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.dto.Doctor.DoctorRequest;
import med.voll.api.dto.Doctor.UpdateDoctorRequest;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerDoctor(@RequestBody @Valid DoctorRequest request) {
        doctorService.registerDoctor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Médico cadastrado com sucesso!");
    }

    @GetMapping
    public Page<DoctorDTO> getActiveDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return doctorService.getActiveDoctors(pagination);
    }

    @GetMapping("/all")
    public Page<DoctorDTO> getAllDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return doctorService.getAllDoctors(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> getDoctorDetail(@PathVariable Long id) {
        DoctorDetailDTO doctorDetailDTO = doctorService.getDoctorDetail(id);

        return ResponseEntity.ok().body(doctorDetailDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorRequest request) throws Exception {
        doctorService.updateDoctor(id, request);

        return ResponseEntity.status(HttpStatus.OK).body("Médico alterado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) throws Exception {
        doctorService.deleteDoctor(id);

        return ResponseEntity.status(HttpStatus.OK).body("Médico deletado com sucesso!");
    }
}
