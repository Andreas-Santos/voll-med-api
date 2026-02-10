package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.request.DoctorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @PostMapping
    public void registerDoctor(@RequestBody @Valid DoctorRequest request) {
        System.out.println(request);
    }
}
