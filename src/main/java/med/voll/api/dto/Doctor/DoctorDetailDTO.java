package med.voll.api.dto.Doctor;

import med.voll.api.model.Address;
import med.voll.api.model.enums.Specialty;

public record DoctorDetailDTO(
        Long id,
        String name,
        String email,
        String phone,
        String crm,
        Specialty specialty,
        Address address
) {
}
