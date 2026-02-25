package med.voll.api.request.Patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.request.AddressRequest;

public record PatientRequest(
        @NotBlank(message = "Nome é obrigatório!")
        String name,

        @NotBlank(message = "Email é obrigatório!")
        String email,

        @NotBlank(message = "Telefone é obrigatório!")
        String phone,

        @NotBlank(message = "CPF é obrigatório!")
        String cpf,

        @NotNull(message = "Endereço é obrigatório!")
        @Valid
        AddressRequest address
) {
}
