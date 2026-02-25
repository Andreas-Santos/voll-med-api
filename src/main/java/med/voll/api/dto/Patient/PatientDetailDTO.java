package med.voll.api.dto.Patient;

import med.voll.api.model.Address;
import med.voll.api.model.Patient;

public record PatientDetailDTO(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Address address
) {
    public PatientDetailDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(),
                patient.getAddress());
    }
}
