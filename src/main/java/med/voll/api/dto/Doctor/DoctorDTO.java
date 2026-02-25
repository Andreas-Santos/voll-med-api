package med.voll.api.dto.Doctor;

import med.voll.api.model.Doctor;
import med.voll.api.model.enums.Specialty;

public record DoctorDTO(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {

    public DoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
