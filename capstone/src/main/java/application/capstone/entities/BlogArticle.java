package application.capstone.entities;


import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.boot.SpringApplicationRunListener;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class BlogArticle {
    @Id
    @GeneratedValue
    private UUID id;
    private String immaginePrimaria;
    private String immagineSecondaria;
    @Enumerated(EnumType.STRING)
    private Set<Genere> genresList;
    private String titolo;
    private String svillupatore;
    private String pubblicazione;
    @Enumerated(EnumType.STRING)
    private Tema tema;
    private String storia;
    private String esperienza;
    private String consigli;
    @OneToMany
    private List<Comment> comments;


    public void setImmaginePrimaria(String immaginePrimaria) {
        this.immaginePrimaria = immaginePrimaria;
    }

    public void setImmagineSecondaria(String immagineSecondaria) {
        this.immagineSecondaria = immagineSecondaria;
    }

    public void setGenresList(Set<Genere> genresList) {
        this.genresList = genresList;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setSvillupatore(String svillupatore) {
        this.svillupatore = svillupatore;
    }

    public void setPubblicazione(String pubblicazione) {
        this.pubblicazione = pubblicazione;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public void setStoria(String storia) {
        this.storia = storia;
    }

    public void setEsperienza(String esperienza) {
        this.esperienza = esperienza;
    }

    public void setConsigli(String consigli) {
        this.consigli = consigli;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
