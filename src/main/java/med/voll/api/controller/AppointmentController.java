package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.Appointment.AppointmentCancelRequest;
import med.voll.api.dto.Appointment.AppointmentDTO;
import med.voll.api.dto.Appointment.AppointmentDetailDTO;
import med.voll.api.dto.Appointment.AppointmentRequest;
import med.voll.api.model.Appointment;
import med.voll.api.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailDTO> registerAppointment(
            @RequestBody @Valid AppointmentRequest request, UriComponentsBuilder uriBuilder) throws Exception {
        Appointment appointment = appointmentService.registerAppointment(request);

        var uri = uriBuilder.path("appointments/{id}").buildAndExpand(appointment.getId()).toUri();

        return ResponseEntity.created(uri).body(new AppointmentDetailDTO(appointment));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentDTO>> getAppointments(
            @PageableDefault(size = 10, sort = {"date"}) Pageable pagination)
    {
        Page<AppointmentDTO> pageAppointments = appointmentService.getAppointments(pagination);

        return ResponseEntity.status(HttpStatus.OK).body(pageAppointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailDTO> getAppointmentDetail(@PathVariable Long id) {
        AppointmentDetailDTO appointmentDetailDTO = appointmentService.getAppointmentDetail(id);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentDetailDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> cancelAppointment(
            @PathVariable Long id, @RequestBody @Valid AppointmentCancelRequest request)
    {
        appointmentService.cancelAppointment(id, request);

        return ResponseEntity.noContent().build();
    }
}
