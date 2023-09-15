package fr.fst.m2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.fst.m2.dto.LivreDto;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final List<LivreDto> livres = List.of(new LivreDto(1, "titre"), new LivreDto(2, "titre2"));

    @GetMapping("")
    public @ResponseBody List<LivreDto> getLivres() {
        return livres;
    }

    @GetMapping("/{id}")
    public @ResponseBody LivreDto getLivre(@PathVariable("id") Integer livreId)  throws Exception {
        return livres.stream()
                .filter(livreDto -> livreDto.getId().equals(livreId))
                .findFirst()
                .orElseThrow(() -> new Exception("Livre with id " + livreId + " doesn't exist"));
    }

}
