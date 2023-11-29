package application.capstone.repositories;

import application.capstone.entities.BlogCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogCardRepository extends JpaRepository<BlogCard , UUID> {
}
