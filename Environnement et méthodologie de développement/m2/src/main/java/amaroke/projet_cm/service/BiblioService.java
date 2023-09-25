package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.BiblioAlreadyExists;
import amaroke.projet_cm.exception.BiblioNotFoundException;
import amaroke.projet_cm.exception.NullListException;
import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.repository.BiblioRepository;
import amaroke.projet_cm.repository.LivreRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiblioService {

    private final BiblioRepository biblioRespository;
    private final LivreRepository livreRepository;
    private final LivreService livreService;

    public List<BiblioEntity> getBiblios() {
        return biblioRespository.findAll();
    }

    public BiblioEntity getBiblio(Integer biblioId) {
        return biblioRespository.findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
    }

    public void addBiblio(BiblioEntity biblioEntity) {
        if (biblioRespository.existsById(biblioEntity.getId())) {
            throw new BiblioAlreadyExists("Biblio with id " + biblioEntity.getId() + " already exists");
        }
        biblioRespository.save(biblioEntity);

    }

    public void addLivreToBiblio(Integer biblioId, Integer livreId) {
        BiblioEntity biblio = biblioRespository.findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
        biblio.getLivres().add(livreService.getLivre(livreId));
        livreService.getLivre(livreId).getBibliotheques().add(biblio);
        livreRepository.save(livreService.getLivre(livreId));
        biblioRespository.save(biblio);
    }

    public void deleteBiblio(int biblioId) {
        BiblioEntity biblioToDelete = this.getBiblio(biblioId);
        List<LivreEntity> livres = biblioToDelete.getLivres();
        livres.forEach(livreEntity -> livreEntity.getBibliotheques().remove(biblioToDelete));
        biblioRespository.deleteById(biblioId);
    }

    public void deleteLivreFromBiblio(Integer biblioId, Integer livreId) {
        BiblioEntity biblio = biblioRespository.findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
        if (biblio.getLivres().isEmpty()) {
            throw new NullListException("Biblio with id " + biblioId + " doesn't have any livres");
        }
        LivreEntity livre = livreService.getLivre(livreId);
        biblio.getLivres().remove(livre);
        livre.getBibliotheques().remove(biblio);
        biblioRespository.save(biblio);
    }

}
