package application.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PutCommentDTO(
        @NotEmpty(message = "il testo del messaggio è un campo obbligatorio")
        @Size(min = 1 , max = 200 , message = "il testo del messaggio deve essere compreso tra 1 e 200 ")
        String comment

) {
}
