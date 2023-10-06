package amaroke.projet_cm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import amaroke.projet_cm.model.dto.request.PostCommentaireDto;
import amaroke.projet_cm.model.dto.request.PostLivreDto;
import amaroke.projet_cm.model.dto.response.GetLivreResponseDto;
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
        return this.livreService.getLivres().stream().map(GetLivreResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public @ResponseBody GetLivreResponseDto getLivreById(@PathVariable("id") @Min(0) Integer livreId) {
        return new GetLivreResponseDto(this.livreService.getLivre(livreId));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLivre(@RequestBody @Valid PostLivreDto livreDto) {
        this.livreService.addLivre(livreDto.getTitre());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLivre(@PathVariable("id") @Min(0) Integer livreId,
            @RequestBody @Valid PostLivreDto livreDto) {
        this.livreService.updateLivre(livreId, livreDto.getTitre());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLivre(@PathVariable("id") @Min(0) Integer livreId) {
        this.livreService.deleteLivre(livreId);
    }

    @PostMapping("/{livreId}/commentaires")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCommentaire(@PathVariable Integer livreId,
            @RequestBody @Valid PostCommentaireDto postCommentaireDto) {
        this.livreService.addCommentaire(livreId, postCommentaireDto.getCommentaire());
    }

    @GetMapping("/{livreId}/cover")
    public String getCover(@PathVariable Integer livreId) {
        this.getLivreById(livreId);
        return this.livreService.getCover(livreId);
    }

    @PutMapping("/{livreId}/cover")
    public String addCover(@PathVariable Integer livreId) {
        this.getLivreById(livreId);
        return this.livreService.addCover(livreId);
    }

}
