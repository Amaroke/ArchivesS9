package amaroke.projet_cm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import amaroke.projet_cm.model.dto.request.PostAddLivreToBiblioDto;
import amaroke.projet_cm.model.dto.request.PostBiblioDto;
import amaroke.projet_cm.model.dto.response.GetBibilioResponseDto;
import amaroke.projet_cm.service.BiblioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/biblios")
@Validated
@RequiredArgsConstructor
public class BiblioController {

    private final BiblioService biblioService;

    @GetMapping("")
    public @ResponseBody List<GetBibilioResponseDto> getBiblios() {
        return this.biblioService.getBiblios().stream().map(GetBibilioResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public @ResponseBody GetBibilioResponseDto getBiblioById(@PathVariable("id") @Min(0) Integer biblioId) {
        return new GetBibilioResponseDto(this.biblioService.getBiblio(biblioId));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBiblio(@RequestBody PostBiblioDto biblioDto) {
        this.biblioService
                .addBiblio(biblioDto.getNom());
    }

    @PostMapping("/{id}/livres")
    @ResponseStatus(HttpStatus.OK)
    public void addLivreToBiblio(@PathVariable("id") @Min(0) Integer biblioId,
            @RequestBody @Valid PostAddLivreToBiblioDto biblioDto) {
        this.biblioService.addLivreToBiblio(biblioId, biblioDto.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBiblio(@PathVariable("id") @Min(0) Integer biblioId) {
        biblioService.deleteBiblio(biblioId);
    }

    @DeleteMapping("/{id}/livres/{livreId}")
    public void deleteLivreFromBiblio(@PathVariable("id") @Min(0) Integer biblioId,
            @PathVariable("livreId") @Min(0) Integer livreId) {
        this.biblioService.deleteLivreFromBiblio(biblioId, livreId);
    }
}
