package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.model.enums.Specialty;
import med.voll.api.request.Doctor.DoctorRequest;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Doctor(DoctorRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.crm = request.crm();
        this.specialty = request.specialty();
        this.address = new Address(request.address());
    }
}
