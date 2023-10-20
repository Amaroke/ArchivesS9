package amaroke.tpnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.tpnote.model.entity.EvaluationFinaleEntity;

@Repository
public interface EvaluationFinaleRepository extends JpaRepository<EvaluationFinaleEntity, Integer> {

}
