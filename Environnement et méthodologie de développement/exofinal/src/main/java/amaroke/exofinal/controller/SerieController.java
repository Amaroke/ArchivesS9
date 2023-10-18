package amaroke.exofinal.controller;

import java.util.Comparator;
import java.util.List;

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

import amaroke.exofinal.model.dto.request.CommentairePostDto;
import amaroke.exofinal.model.dto.request.SerieRequestPostDto;
import amaroke.exofinal.model.dto.response.SerieResponseDto;
import amaroke.exofinal.model.entity.SerieEntity;
import amaroke.exofinal.service.SerieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/series")
@Validated
@RequiredArgsConstructor
public class SerieController {

    private final SerieService serieService;

    @GetMapping("")
    public @ResponseBody List<SerieResponseDto> getAllSeries() {
        List<SerieResponseDto> sortedSeries = this.serieService.getAllSeries().stream()
                .sorted(Comparator.comparing(SerieEntity::getDateSortie))
                .map(SerieResponseDto::new)
                .toList();

        return sortedSeries;
    }

    @GetMapping("/search/{query}")
    public @ResponseBody List<SerieResponseDto> searchSeries(@PathVariable("query") String query) {
        List<SerieResponseDto> sortedSeries = this.serieService.searchSeries(query).stream()
                .sorted(Comparator.comparing(SerieEntity::getDateSortie))
                .map(SerieResponseDto::new)
                .toList();

        return sortedSeries;
    }

    @GetMapping("/{serieId}")
    public @ResponseBody SerieResponseDto getSerieById(@Valid @Min(0) @PathVariable("serieId") Integer id) {
        return new SerieResponseDto(this.serieService.getSerieById(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSerie(@RequestBody @Valid SerieRequestPostDto serieRequestPostDto) {
        this.serieService.addSerie(
                serieRequestPostDto.getTitre(),
                serieRequestPostDto.getDescription(),
                serieRequestPostDto.getDateSortie());

    }

    @DeleteMapping("/{serieId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSerieById(@Valid @Min(0) @PathVariable("serieId") Integer id) {
        this.serieService.deleteSerieById(id);
    }

    @PostMapping("/{serieId}/commentaires")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCommentaire(@PathVariable("serieId") Integer id,
            @RequestBody @Valid CommentairePostDto commentairePostDto) {
        this.serieService.addCommentaire(id,
                commentairePostDto.getAuteur(),
                commentairePostDto.getNote(),
                commentairePostDto.getCommentaire());
    }

    @GetMapping("/{serieId}/cover")
    public String getCover(@PathVariable Integer serieId) {
        this.getSerieById(serieId);
        return this.serieService.getCover(serieId);
    }

    @PutMapping("/{livreId}/cover")
    public String addCover(@PathVariable Integer livreId) {
        this.getSerieById(livreId);
        return this.serieService.addCover(livreId);
    }

    @GetMapping("/{serieId}/search/{query}")
    public String searchCommentaires(@PathVariable Integer serieId, @PathVariable String query) {
        this.getSerieById(serieId);
        return this.serieService.searchSeries(query).toString();
    }

}
