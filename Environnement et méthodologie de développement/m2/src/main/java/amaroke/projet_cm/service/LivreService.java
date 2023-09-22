package amaroke.projet_cm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.LivreAlreadyExists;
import amaroke.projet_cm.exception.LivreNotFoundException;
import amaroke.projet_cm.exception.NullListException;
import amaroke.projet_cm.model.dto.service.LivreModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final ArrayList<LivreModel> livres = new ArrayList<>(List.of(
            new LivreModel(1, "Livre 1"),
            new LivreModel(2, "Livre 2")));

    public List<LivreModel> getLivres() {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres;
        }
    }

    public LivreModel getLivre(Integer id) {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres.stream()
                    .filter(livreDto -> livreDto.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new LivreNotFoundException("Livre with id " + id + " doesn't exist"));
        }
    }

    public void addLivre(LivreModel livreDto) {
        if (livres.stream().anyMatch(livre -> livre.getId().equals(livreDto.getId()))) {
            throw new LivreAlreadyExists("Livre with id " + livreDto.getId() + " already exists");
        }
        livres.add(livreDto);
    }

    public void updateLivre(LivreModel livreDto, Integer livreId) {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            LivreModel livre = livres.stream()
                    .filter(livreDto1 -> livreDto1.getId().equals(livreId))
                    .findFirst()
                    .orElseThrow(() -> new LivreNotFoundException("Livre with id " + livreId + " doesn't exist"));
            livre.setTitre(livreDto.getTitre());
        }
    }

    public void deleteLivre(int id) {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            LivreModel livre = livres.stream()
                    .filter(livreDto -> livreDto.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new LivreNotFoundException("Livre with id " + id + " doesn't exist"));
            livres.remove(livre);
        }
    }

}