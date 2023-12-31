package application.capstone.payloads;

import application.capstone.entities.BlogArticle;
import application.capstone.entities.BlogCard;
import application.capstone.enums.Tema;
import application.capstone.validatorEnums.TemaEnumValidator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
        String tema,
        @NotEmpty(message = "la storia è un campo obbligatorio!")
        @Size(min = 20, max=900, message = "la storia deve essere compreso tra 20 e 900 caratteri")
        String storia,
        @NotEmpty(message = "l'esperienza è un campo obbligatorio!")
        @Size(max=400, message = "l'esperienza deve essere massimo di 400 caratteri")
        String esperienza,
        @NotEmpty(message = "i consigli è un campo obbligatorio!")
        @Size(max=400, message = "i consigli deveno essere massimo di e 400 caratteri")
        String consigli,
        @NotEmpty(message = "inserire almeno un genere")
        String[] genere,
        @NotEmpty(message = "una breve descrizione è un campo obbligatorio!")
        @Size(min = 10, max=30, message = "la descrizione deve essere lunga tra i 10 e i 30 caratteri")
        String descrizione
) {
}
