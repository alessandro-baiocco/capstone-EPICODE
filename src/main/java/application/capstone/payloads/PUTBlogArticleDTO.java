package application.capstone.payloads;

import application.capstone.enums.Tema;
import application.capstone.validatorEnums.TemaEnumValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PUTBlogArticleDTO(@NotEmpty(message = "Il titolo è un campo obbligatorio!")
                                @Size(min = 1, max = 30, message = "Il titolo deve essere compreso tra 1 e 30 caratteri")
                                String titolo,
                                @NotEmpty(message = "lo svillupatore è un campo obbligatorio!")
                                @Size(min = 1, max = 30, message = "lo svillupatore deve essere compreso tra 1 e 30 caratteri")
                                String svillupatore,
                                @NotEmpty(message = "Il nome di chi ha pubblicato il titolo è un campo obbligatorio!")
                                @Size(min = 1, max = 30, message = "Il nome di chi ha pubblicato il titolo deve essere compreso tra 1 e 30 caratteri")
                                String pubblicazione,
                                @NotNull(message = "il tema è un campo obbligatorio")
                                @TemaEnumValidator
                                Tema tema,
                                @NotEmpty(message = "la storia è un campo obbligatorio!")
                                @Size(min = 20, max = 900, message = "la storia deve essere compreso tra 20 e 600 caratteri")
                                String storia,
                                @NotEmpty(message = "l'esperienza è un campo obbligatorio!")
                                @Size(min = 20, max = 400, message = "l'esperienza deve essere compreso tra 20 e 200 caratteri")
                                String esperienza,
                                @NotEmpty(message = "i consigli è un campo obbligatorio!")
                                @Size(min = 20, max = 400, message = "il tema deve essere compreso tra 20 e 200 caratteri")
                                String consigli,
                                @NotEmpty(message = "inserire almeno un genere o più separtati da ',' è un campo obbligatorio!")
                                String[] genresList
) {
}
