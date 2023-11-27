package application.capstone.entities;


import application.capstone.enums.Genere;
import application.capstone.enums.Tema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.boot.SpringApplicationRunListener;

import java.util.List;
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
    private List<Genere> genresList;
    private String titolo;
    private String svillupatore;
    private String pubblicazione;
    private Tema tema;
    private String storia;
    private String esperienza;
    private String consigli;
    @OneToOne
    @JoinColumn(name = "blogCard_id")
    private BlogCard blogCard;



}
