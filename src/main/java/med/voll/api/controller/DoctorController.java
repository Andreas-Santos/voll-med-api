package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.Doctor.DoctorDTO;
import med.voll.api.dto.Doctor.DoctorDetailDTO;
import med.voll.api.request.Doctor.DoctorRequest;
import med.voll.api.request.Doctor.UpdateDoctorRequest;
import med.voll.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    public void registerDoctor(@RequestBody @Valid DoctorRequest request) {
        doctorService.registerDoctor(request);
    }

    @GetMapping
    public List<DoctorDetailDTO> getDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("/top10")
    public List<DoctorDTO> getTop10DoctorsOrderedByNameAsc() {
        return doctorService.getTop10DoctorsOrderedByNameAsc();
    }

    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id, @RequestBody UpdateDoctorRequest request) throws Exception {
        doctorService.updateDoctor(id, request);
    }
}
