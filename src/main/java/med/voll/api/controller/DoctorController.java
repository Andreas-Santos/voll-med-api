package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDTO;
import med.voll.api.request.DoctorRequest;
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
    public List<DoctorDTO> getTop10DoctorsOrderedByNameAsc() {
        return doctorService.getTop10DoctorsOrderedByNameAsc();
    }
}
