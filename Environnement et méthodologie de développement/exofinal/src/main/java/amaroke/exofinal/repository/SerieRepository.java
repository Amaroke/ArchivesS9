package amaroke.exofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.exofinal.model.entity.SerieEntity;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Integer> {
}
