package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.BiblioAlreadyExists;
import amaroke.projet_cm.exception.BiblioNotFoundException;
import amaroke.projet_cm.exception.NullListException;
import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.repository.BiblioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiblioService {

    private final BiblioRepository biblioRespository;
    private final LivreService livreService;

    public List<BiblioEntity> getBiblios() {
        return biblioRespository.findAll();
    }

    public BiblioEntity getBiblio(Integer id) {
        return biblioRespository.findById(id)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + id + " doesn't exist"));
    }

    public void addBiblio(BiblioEntity biblioDto) {
        if (biblioRespository.existsById(biblioDto.getId())) {
            throw new BiblioAlreadyExists("Biblio with id " + biblioDto.getId() + " already exists");
        }
        biblioRespository.save(biblioDto);

    }

    public void addLivreToBiblio(Integer biblioId, Integer livreId) {
        BiblioEntity biblio = biblioRespository.findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
        biblio.getLivres().add(livreService.getLivre(livreId));
        biblioRespository.save(biblio);
    }

    public void deleteBiblio(int id) {
        biblioRespository.deleteById(id);
    }

    public void deleteLivreFromBiblio(Integer biblioId, Integer livreId) {
        BiblioEntity biblio = biblioRespository.findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
        if (biblio.getLivres().isEmpty()) {
            throw new NullListException("Biblio with id " + biblioId + " doesn't have any livres");
        }
        biblio.getLivres().remove(livreService.getLivre(livreId));
        biblioRespository.save(biblio);

    }

}
