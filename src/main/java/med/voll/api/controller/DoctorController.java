package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.request.Doctor.DoctorRequest;
import med.voll.api.request.Doctor.UpdateDoctorRequest;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid DoctorRequest request) {
        doctorService.registerDoctor(request);
    }

    @GetMapping
    public Page<DoctorDetailDTO> getDoctors(Pageable pagination) {
        return doctorService.getDoctors(pagination);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorRequest request) throws Exception {
        doctorService.updateDoctor(id, request);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) throws Exception {
        doctorService.deleteDoctor(id);
    }
}
