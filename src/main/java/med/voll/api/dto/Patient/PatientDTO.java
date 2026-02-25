package med.voll.api.dto.Patient;

import med.voll.api.model.Patient;

public record PatientDTO(
        String name,
        String email,
        String cpf
) {
    public PatientDTO(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
