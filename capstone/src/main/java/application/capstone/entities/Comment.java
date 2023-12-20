package application.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private UUID id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "article_id")
    private BlogArticle blogArticle;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBlogArticle(BlogArticle blogArticle) {
        this.blogArticle = blogArticle;
    }
}
