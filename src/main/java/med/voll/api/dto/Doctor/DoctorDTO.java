package med.voll.api.dto.Doctor;

import med.voll.api.model.enums.Specialty;

public record DoctorDTO(
        String name,
        String email,
        String crm,
        Specialty specialty
) {
}
