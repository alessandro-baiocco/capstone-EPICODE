package application.capstone.entities;

import application.capstone.enums.Genere;
import application.capstone.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
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
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role ruolo;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGenerePreferito(Genere generePreferito) {
        this.generePreferito = generePreferito;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }
}
