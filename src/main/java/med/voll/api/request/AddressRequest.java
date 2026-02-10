package med.voll.api.request;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
    @NotBlank(message = "Logradouro é obrigatório!")
    String streetAddress,
    Integer number,
    String additionalInformation,
    @NotBlank(message = "Bairro é obrigatório!")
    String neighborhood,
    @NotBlank(message = "Cidade é obrigatória!")
    String city,
    @NotBlank(message = "UF é obrigatória!")
    String state,
    @NotBlank(message = "CEP é obrigatório!")
    String postalCode
) {
}
