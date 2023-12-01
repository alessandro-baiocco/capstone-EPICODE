package application.capstone.payloads;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.BlogCard;
import application.capstone.enums.Tema;
import application.capstone.validatorEnums.TemaEnumValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NewBlogArticleDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        @Size(min = 1, max=30, message = "Il titolo deve essere compreso tra 1 e 30 caratteri")
        String titolo,
        @NotEmpty(message = "lo svillupatore è un campo obbligatorio!")
        @Size(min = 1, max=30, message = "lo svillupatore deve essere compreso tra 1 e 30 caratteri")
        String svillupatore,
        @NotEmpty(message = "Il nome di chi ha pubblicato il titolo è un campo obbligatorio!")
        @Size(min = 1, max=30, message = "Il nome di chi ha pubblicato il titolo deve essere compreso tra 1 e 30 caratteri")
        String pubblicazione,
        @NotNull(message = "il tema è un campo obbligatorio")
        @TemaEnumValidator
        Tema tema,
        @NotEmpty(message = "la storia è un campo obbligatorio!")
        @Size(min = 20, max=500, message = "la storia deve essere compreso tra 20 e 500 caratteri")
        String storia,
        @NotEmpty(message = "l'esperienza è un campo obbligatorio!")
        @Size(min = 20, max=400, message = "l'esperienza deve essere compreso tra 20 e 400 caratteri")
        String esperienza,
        @NotEmpty(message = "i consigli è un campo obbligatorio!")
        @Size(min = 20, max=400, message = "il tema deve essere compreso tra 20 e 400 caratteri")
        String consigli,
        @NotEmpty(message = "inserire almeno un genere o più separtati da ',' è un campo obbligatorio!")
        @Size(min = 4, max=16, message = "inserire almeno un genere massimo 3 ")
        String genere,
        @NotEmpty(message = "una breve descrizione è un campo obbligatorio!")
        @Size(min = 10, max=30, message = "la descrizione deve essere lunga tra i 10 e i 30 caratteri")
        String desciption,
        @NotNull(message = "l'id utente è un campo obbligatorio!")
        UUID user
) {
}
