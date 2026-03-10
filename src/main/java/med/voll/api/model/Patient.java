package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import med.voll.api.dto.Patient.PatientRequest;
import med.voll.api.dto.Patient.UpdatePatientRequest;

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

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String cpf;

    private Boolean active = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    @Valid
    private Address address;

    public Patient(PatientRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.cpf = request.cpf();
        this.address = new Address(request.address());
    }

    public void updatePatient(UpdatePatientRequest request) {
        if(request.name() != null) setName(request.name());

        if(request.phone() != null) setPhone(request.phone());

        if(request.address() != null) {
            Address address = new Address(request.address());
            setAddress(address);
        }
    }

    public void inactivatePatient() {
        active = false;
    }
}
