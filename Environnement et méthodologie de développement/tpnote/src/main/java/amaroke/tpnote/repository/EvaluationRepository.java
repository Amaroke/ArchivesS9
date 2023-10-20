package amaroke.tpnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.tpnote.model.entity.EvaluationEntity;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Integer> {

}
