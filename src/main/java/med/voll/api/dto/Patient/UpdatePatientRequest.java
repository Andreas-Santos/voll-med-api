package med.voll.api.dto.Patient;

import jakarta.validation.Valid;
import med.voll.api.dto.AddressRequest;

public record UpdatePatientRequest(
        String name,
        String phone,

        @Valid
        AddressRequest address
) {
}
