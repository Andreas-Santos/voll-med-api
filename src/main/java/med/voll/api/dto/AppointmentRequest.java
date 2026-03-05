package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotNull(message = "O id do paciente é obrigatório!")
        Long patient,
        
        Long doctor,

        @NotBlank
        LocalDateTime date
) {
}
