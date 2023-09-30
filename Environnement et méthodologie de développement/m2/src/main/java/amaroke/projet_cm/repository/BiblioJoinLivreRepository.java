package amaroke.projet_cm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.BiblioJoinLivreEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.model.entity.key.BiblioJoinLivreId;

@Repository
public interface BiblioJoinLivreRepository extends JpaRepository<BiblioJoinLivreEntity, BiblioJoinLivreId> {

    public List<BiblioJoinLivreEntity> findAllByLivreIs(LivreEntity livre);

    public void deleteAllByLivreIs(LivreEntity livre);

    public void deleteAllByBiblioIs(BiblioEntity biblio);

}
