package med.voll.api.dto.Doctor;

import jakarta.validation.Valid;
import med.voll.api.dto.AddressRequest;

public record UpdateDoctorRequest(
        String name,
        String phone,

        @Valid
        AddressRequest address
) {
}
