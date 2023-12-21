package application.capstone.payloads;

import application.capstone.enums.Genere;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record NewUserDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String cognome,
        @NotEmpty(message = "Il nome utente è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String username,
        @NotEmpty(message = "la password è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String password,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @Size( max=30, message = "la descrizione deve essere massimo di 30 caratteri")
        String descrizione,
        String generePreferito

        ) {
}
