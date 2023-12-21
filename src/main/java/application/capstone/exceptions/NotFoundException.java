package application.capstone.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id){

        super("l'oggetto con " + id + " non è stato trovato");
    }

    public NotFoundException(String email){

        super("l'utente con email " + email + " non è stato trovato");
    }

}
