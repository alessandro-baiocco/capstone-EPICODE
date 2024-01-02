package application.capstone.entities;


import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Lob
    @Column(name = "storia", columnDefinition="VARCHAR(900)")
    private String storia;
    @Lob
    @Column(name = "esperienza", columnDefinition="VARCHAR(600)")
    private String esperienza;
    @Lob
    @Column(name = "consigli", columnDefinition="VARCHAR(600)")
    private String consigli;
    @OneToMany(mappedBy = "blogArticle" , cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @JsonIgnore
    @OneToOne(mappedBy = "blogArticle" , cascade = CascadeType.REMOVE)
    private BlogCard blogCard;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;


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

    public void setUser(User user) {
        this.user = user;
    }

    public void setBlogCard(BlogCard blogCard) {
        this.blogCard = blogCard;
    }

    @Override
    public String toString() {
        return "BlogArticle{"+ id +
               genresList +
                ", titolo='" + titolo + '\'' +
                ", svillupatore='" + svillupatore + '\'' +
                ", pubblicazione='" + pubblicazione + '\'' +
                ", tema=" + tema +
                ", storia='" + storia + '\'' +
                ", esperienza='" + esperienza + '\'' +
                ", consigli='" + consigli;
    }
}
