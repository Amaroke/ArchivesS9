package amaroke.projet_cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.projet_cm.model.entity.CommentaireEntity;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Integer> {
}
