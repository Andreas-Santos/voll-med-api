package med.voll.api.dto.Appointment;

import jakarta.validation.constraints.NotBlank;

public record AppointmentCancelRequest(
        @NotBlank(message = "Mensagem de cancelamento obrigatória!")
        String cancelDescription
) {
}
