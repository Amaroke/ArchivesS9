package amaroke.projet_cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.projet_cm.model.entity.AuteurEntity;

@Repository
public interface AuteurRepository extends JpaRepository<AuteurEntity, String> {

}
