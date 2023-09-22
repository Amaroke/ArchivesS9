package amaroke.projet_cm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.BibblioAlreadyExists;
import amaroke.projet_cm.exception.BiblioNotFoundException;
import amaroke.projet_cm.exception.NullListException;
import amaroke.projet_cm.model.dto.service.BiblioModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiblioService {

    private final LivreService livreService;

    private final ArrayList<BiblioModel> biblios = new ArrayList<>(List.of(
            new BiblioModel(1, "Biblio 1", new ArrayList<>())));

    public List<BiblioModel> getBiblios() {
        if (biblios == null) {
            throw new NullListException("No biblios found");
        }
        return biblios;
    }

    public BiblioModel getBiblio(Integer id) {
        return biblios.stream()
                .filter(biblioDto -> biblioDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + id + " doesn't exist"));
    }

    public void addBiblio(BiblioModel biblioDto) {
        if (biblios.stream().anyMatch(livre -> livre.getId().equals(biblioDto.getId()))) {
            throw new BibblioAlreadyExists("Biblio with id " + biblioDto.getId() + " already exists");
        }
        biblios.add(biblioDto);
    }

    public void addLivreToBiblio(Integer biblioId, Integer livreId) {
        this.getBiblio(biblioId).getLivres().add(livreService.getLivre(livreId));
    }

    public void deleteBiblio(int id) {
        if (biblios == null) {
            throw new NullListException("No biblios found");
        } else {
            BiblioModel biblio = biblios.stream()
                    .filter(biblioDto -> biblioDto.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + id + " doesn't exist"));
            biblios.remove(biblio);
        }
    }

    public void deleteLivreFromBiblio(Integer biblioId, Integer livreId) {
        this.getBiblio(biblioId).getLivres().remove(livreService.getLivre(livreId));
    }

}
