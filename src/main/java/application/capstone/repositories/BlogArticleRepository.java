package application.capstone.repositories;

import application.capstone.entities.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogArticleRepository extends JpaRepository<BlogArticle, UUID> {
}
