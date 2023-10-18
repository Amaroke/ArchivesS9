package amaroke.exofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.exofinal.model.entity.CommentaireEntity;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Integer> {
}
