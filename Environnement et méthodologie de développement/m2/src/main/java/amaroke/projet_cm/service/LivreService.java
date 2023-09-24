package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.LivreNotFoundException;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.repository.LivreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final LivreRepository livreRespository;

    public List<LivreEntity> getLivres() {
        return livreRespository.findAll();
    }

    public LivreEntity getLivre(Integer id) {
        return livreRespository.findById(id)
                .orElseThrow(() -> new LivreNotFoundException("Livre with id " + id + " doesn't exist"));
    }

    public void addLivre(LivreEntity livreEntity) {
        livreRespository.save(livreEntity);
    }

    public void updateLivre(Integer livreId, String titre) {
        LivreEntity livre = livreRespository.findById(livreId)
                .orElseThrow(() -> new LivreNotFoundException("Livre with id " + livreId + " doesn't exist"));
        livre.setTitre(titre);
        livreRespository.save(livre);
    }

    public void deleteLivre(int id) {
        LivreEntity livreToDelete = this.getLivre(id);
        livreToDelete.getBibliotheques().forEach(biblio -> biblio.getLivres().remove(livreToDelete));
        livreRespository.deleteById(id);
    }

}