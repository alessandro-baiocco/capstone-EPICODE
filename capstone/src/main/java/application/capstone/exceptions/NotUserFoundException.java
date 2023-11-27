package application.capstone.exceptions;

public class NotUserFoundException extends RuntimeException {
    public NotUserFoundException(int id){

        super("l'utente con id " + id + " non è stato trovato");
    }

    public NotUserFoundException(String email){

        super("l'utente con email " + email + " non è stato trovato");
    }

}
