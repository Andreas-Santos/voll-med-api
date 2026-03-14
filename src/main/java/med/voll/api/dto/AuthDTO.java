package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "Usuário é obrigatório!")
        String username,

        @NotBlank(message = "Senha é obrigatória!")
        String password
) {
}
