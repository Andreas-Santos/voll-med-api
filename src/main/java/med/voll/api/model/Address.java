package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.request.AddressRequest;

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

    private String streetAddress;
    private Integer number;
    private String additionalInformation;
    private String neighborhood;
    private String city;
    private String state;
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
