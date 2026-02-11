package med.voll.api.request.Doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.model.enums.Specialty;
import med.voll.api.request.AddressRequest;

public record DoctorRequest(
    @NotBlank(message = "Nome é obrigatório!")
    String name,

    @NotBlank(message = "Email é obrigatório!")
    @Email
    String email,

    @NotBlank(message = "Telefone é obrigatório!")
    String phone,

    @NotBlank(message = "CRM é obrigatório!")
    String crm,

    @NotNull(message = "Especialidade é obrigatória!")
    Specialty specialty,

    @NotNull(message = "Endereço é obrigatório!")
    @Valid
    AddressRequest address
) {
}
