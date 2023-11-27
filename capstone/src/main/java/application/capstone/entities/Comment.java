package application.capstone.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private UUID id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private BlogArticle blogArticle;

}
