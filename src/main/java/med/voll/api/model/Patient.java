package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.request.Patient.PatientRequest;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Patient(PatientRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.cpf = request.cpf();
        this.address = new Address(request.address());
    }
}
