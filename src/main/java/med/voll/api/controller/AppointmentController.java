package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Appointment.AppointmentCancelRequest;
import med.voll.api.dto.Appointment.AppointmentRequest;
import med.voll.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> cancelAppointment(
            @PathVariable Long id, @RequestBody @Valid AppointmentCancelRequest request)
    {
        appointmentService.cancelAppointment(id, request);

        return ResponseEntity.status(HttpStatus.OK).body("Consulta cancelada com sucesso!");
    }
}
