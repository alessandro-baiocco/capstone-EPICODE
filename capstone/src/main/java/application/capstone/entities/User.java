package application.capstone.entities;

import application.capstone.enums.Genere;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    @OneToMany
    private List<Comment> comments;
    private String password;
    private String avatar;
    private Genere generePreferito;




}
