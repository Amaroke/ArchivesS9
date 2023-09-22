package amaroke.projet_cm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import amaroke.projet_cm.dto.LivreDto;
import amaroke.projet_cm.exceptions.LivreAlreadyExists;
import amaroke.projet_cm.exceptions.LivreNotFoundException;
import amaroke.projet_cm.exceptions.NullListException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livres")
@Validated
public class LivreController {

    private final ArrayList<LivreDto> livres = new ArrayList<>(List.of(
            new LivreDto(1, "Livre 1"),
            new LivreDto(2, "Livre 2")));

    @GetMapping("")
    public @ResponseBody List<LivreDto> getLivres() {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres;
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody LivreDto getLivre(@PathVariable("id") @Min(0) Integer livreId) {
        if (livres == null || livres.isEmpty()) {
            throw new NullListException("No livres found");
        } else {
            return livres.stream()
                    .filter(livreDto -> livreDto.getId().equals(livreId))
                    .findFirst()
                    .orElseThrow(() -> new LivreNotFoundException("Livre with id " + livreId + " doesn't exist"));
        }
    }

    @PostMapping("")
    public void addLivre(@RequestBody @Valid LivreDto livreDto) {
        if (livres.stream().anyMatch(livre -> livre.getId().equals(livreDto.getId()))) {
            throw new LivreAlreadyExists("Livre with id " + livreDto.getId() + " already exists");
        }
        livres.add(livreDto);
    }
}
