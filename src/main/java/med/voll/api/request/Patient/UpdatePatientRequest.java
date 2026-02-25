package med.voll.api.request.Patient;

import jakarta.validation.Valid;
import med.voll.api.request.AddressRequest;

public record UpdatePatientRequest(
        String name,
        String phone,

        @Valid
        AddressRequest address
) {
}
