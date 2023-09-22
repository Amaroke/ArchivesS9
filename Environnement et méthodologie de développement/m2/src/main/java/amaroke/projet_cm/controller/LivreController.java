package amaroke.projet_cm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import amaroke.projet_cm.model.dto.request.PostLivreDto;
import amaroke.projet_cm.model.dto.response.GetLivreResponseDto;
import amaroke.projet_cm.model.dto.service.LivreModel;
import amaroke.projet_cm.service.LivreService;

import java.util.List;

@RestController
@RequestMapping("/livres")
@Validated
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @GetMapping("")
    public @ResponseBody List<GetLivreResponseDto> getLivres() {
        return livreService.getLivres().stream().map(GetLivreResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public @ResponseBody GetLivreResponseDto getLivre(@PathVariable("id") @Min(0) Integer livreId) {
        return new GetLivreResponseDto(livreService.getLivre(livreId));
    }

    @PostMapping("")
    public void addLivre(@RequestBody @Valid PostLivreDto livreDto) {
        livreService.addLivre(new LivreModel(livreDto.getId(), livreDto.getTitre()));
    }

    @PutMapping("/{id}")
    public void updateLivre(@PathVariable("id") @Min(0) Integer livreId, @RequestBody @Valid PostLivreDto livreDto) {
        livreService.updateLivre(new LivreModel(livreDto.getId(), livreDto.getTitre()), livreId);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable("id") @Min(0) Integer livreId) {
        livreService.deleteLivre(livreId);
    }
}
