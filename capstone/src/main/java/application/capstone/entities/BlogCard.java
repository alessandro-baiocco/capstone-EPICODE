package application.capstone.entities;

import application.capstone.enums.Genere;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class BlogCard {
    @Id
    @GeneratedValue
    private UUID id;
    private String titolo;
    @Enumerated(EnumType.STRING)
    private Genere genere;
    private String description;
    @OneToOne
    @JoinColumn(name = "blogArticle_id")
    private BlogArticle blogArticle;


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBlogArticle(BlogArticle blogArticle) {
        this.blogArticle = blogArticle;
    }
}
