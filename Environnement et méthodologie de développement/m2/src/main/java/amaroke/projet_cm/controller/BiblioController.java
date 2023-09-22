package amaroke.projet_cm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amaroke.projet_cm.model.dto.request.PostAddLivreToBiblioDto;
import amaroke.projet_cm.model.dto.request.PostBiblioDto;
import amaroke.projet_cm.model.dto.response.GetBibilioResponseDto;
import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.service.BiblioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/biblios")
@Validated
@RequiredArgsConstructor
public class BiblioController {

    private final BiblioService biblioService;

    @GetMapping("")
    public List<GetBibilioResponseDto> getBiblios() {
        return biblioService.getBiblios().stream().map(GetBibilioResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public GetBibilioResponseDto getBiblio(@PathVariable("id") Integer biblioId) {
        return new GetBibilioResponseDto(biblioService.getBiblio(biblioId));
    }

    @PostMapping("")
    public void addBiblio(@RequestBody PostBiblioDto biblioDto) {
        biblioService
                .addBiblio(new BiblioEntity(biblioDto.getId(), biblioDto.getNom(), new ArrayList<LivreEntity>()));
    }

    @PostMapping("/{id}/livres")
    public void addLivreToBiblio(@PathVariable("id") Integer biblioId,
            @RequestBody @Valid PostAddLivreToBiblioDto postAddLivreToBiblioRequestDto) {
        biblioService.addLivreToBiblio(biblioId, postAddLivreToBiblioRequestDto.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteBiblio(@PathVariable("id") Integer biblioId) {
        biblioService.deleteBiblio(biblioId);
    }

    @DeleteMapping("/{id}/livres/{livreId}")
    public void deleteLivreFromBiblio(@PathVariable("id") Integer biblioId,
            @PathVariable("livreId") Integer livreId) {
        biblioService.deleteLivreFromBiblio(biblioId, livreId);
    }
}
