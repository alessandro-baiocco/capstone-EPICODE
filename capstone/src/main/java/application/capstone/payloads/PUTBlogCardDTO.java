package application.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PUTBlogCardDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        @Size(min = 1, max=10, message = "Il titolo deve essere compreso tra 1 e 10 caratteri")
        String titolo,
        @NotEmpty(message = "Il genere è un campo obbligatorio!")
        @Size(min = 4, max=17, message = "Il genere deve essere compreso tra 1 e 17 caratteri")
        String genere,
        @NotEmpty(message = "la descrizione è un campo obbligatorio!")
        @Size(min = 1, max=20, message = "la descrizione deve essere compresa tra 1 e 20 caratteri")
        String description
) {
}
