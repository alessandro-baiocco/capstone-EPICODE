package application.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PUTUserDTO(@NotEmpty(message = "Il nome è un campo obbligatorio!")
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
                          String email,
                         @Size( max=100, message = "la descrizione deve essere massimo di 100 caratteri")
                         String descrizione,
                         String generePreferito) {}