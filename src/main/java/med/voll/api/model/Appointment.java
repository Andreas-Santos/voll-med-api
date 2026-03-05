package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.AppointmentRequest;

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

    private Patient patient;

    private Doctor doctor;

    private LocalDateTime date;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }
}
