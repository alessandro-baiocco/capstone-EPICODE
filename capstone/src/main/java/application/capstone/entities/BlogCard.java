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
    private List<Genere> genresList;
    private String description;
    @OneToOne
    @JoinColumn(name = "blogArticle_id")
    private BlogArticle blogArticle;



}
