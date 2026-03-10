package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.model.enums.Specialty;
import med.voll.api.dto.Doctor.DoctorRequest;
import med.voll.api.dto.Doctor.UpdateDoctorRequest;

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

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String crm;

    private Boolean active = true;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    @Valid
    private Address address;

    public Doctor(DoctorRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.crm = request.crm();
        this.specialty = request.specialty();
        this.address = new Address(request.address());
    }

    public void updateDoctor(UpdateDoctorRequest request) {
        if(request.name() != null) setName(request.name());

        if(request.phone() != null);

        if(request.address() != null) {
            Address address = new Address(request.address());
            setAddress(address);
        }
    }

    public void inactivateDoctor() {
        active = false;
    }
}
