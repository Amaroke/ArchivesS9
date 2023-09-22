package amaroke.projet_cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.projet_cm.model.entity.BiblioEntity;

@Repository
public interface BiblioRepository extends JpaRepository<BiblioEntity, Integer> {
}
