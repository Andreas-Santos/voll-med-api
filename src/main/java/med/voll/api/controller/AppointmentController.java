package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AppointmentRequest;
import med.voll.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerAppointment(@RequestBody @Valid AppointmentRequest request) throws Exception {
        appointmentService.registerAppointment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento criado com sucesso!");
    }
}
