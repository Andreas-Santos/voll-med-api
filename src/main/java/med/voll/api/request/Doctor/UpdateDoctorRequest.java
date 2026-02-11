package med.voll.api.request.Doctor;

import med.voll.api.model.Address;

public record UpdateDoctorRequest(
        String name,
        String phone,
        Address address
) {
}
