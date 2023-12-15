package application.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PUTBlogCardDTO(
        @NotEmpty(message = "la descrizione è un campo obbligatorio!")
        @Size(min = 1, max=20, message = "la descrizione deve essere compresa tra 1 e 20 caratteri")
        String descrizione
) {
}
