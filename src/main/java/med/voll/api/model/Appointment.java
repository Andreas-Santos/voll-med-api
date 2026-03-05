package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.Appointment.AppointmentCancelRequest;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime date;

    private Boolean canceled = false;

    private String cancelDescription = null;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public void cancelAppointment(AppointmentCancelRequest request) {
        this.canceled = true;
        this.cancelDescription = request.cancelDescription();
    }
}
