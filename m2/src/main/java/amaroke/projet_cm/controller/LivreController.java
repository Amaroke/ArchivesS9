package amaroke.projet_cm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import amaroke.projet_cm.dto.LivreDto;
import amaroke.projet_cm.exceptions.LivreNotFoundException;
import amaroke.projet_cm.exceptions.NullListException;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final List<LivreDto> livres = null;

    @GetMapping("")
    public @ResponseBody List<LivreDto> getLivres() {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres;
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody LivreDto getLivre(@PathVariable("id") Integer livreId) {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres.stream()
                    .filter(livreDto -> livreDto.getId().equals(livreId))
                    .findFirst()
                    .orElseThrow(() -> new LivreNotFoundException("Livre with id " + livreId + " doesn't exist"));
        }
    }

}
