package med.voll.api.dto;

import med.voll.api.model.Address;
import med.voll.api.model.enums.Specialty;

public record DoctorDTO(
        String name,
        String email,
        String crm,
        Specialty specialty
) {
}
