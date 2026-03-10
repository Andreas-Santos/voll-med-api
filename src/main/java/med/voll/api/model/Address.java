package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.AddressRequest;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String streetAddress;

    private Integer number;

    private String additionalInformation;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;

    public Address(AddressRequest request) {
        this.streetAddress = request.streetAddress();
        this.number = request.number();
        this.additionalInformation = request.additionalInformation();
        this.neighborhood = request.neighborhood();
        this.city = request.city();
        this.state = request.state();
        this.postalCode = request.postalCode();
    }
}
