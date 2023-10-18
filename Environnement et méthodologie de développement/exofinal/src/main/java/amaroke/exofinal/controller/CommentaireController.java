package amaroke.exofinal.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amaroke.exofinal.service.CommentaireService;
import amaroke.exofinal.service.SerieService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/commentaires")
@Validated
@RequiredArgsConstructor
public class CommentaireController {

    private final SerieService serieService;
    private final CommentaireService commentaireService;

    @GetMapping("/{serieId}/screenshot")
    public String getScreenShot(@PathVariable Integer serieId) {
        this.serieService.getSerieById(serieId);
        return this.commentaireService.getScreenshot(serieId);
    }

    @PutMapping("/{livreId}/screenshot")
    public String addScreenshot(@PathVariable Integer livreId) {
        this.serieService.getSerieById(livreId);
        return this.commentaireService.addScreenshot(livreId);
    }

    @GetMapping("/{serieId}/search/{query}")
    public String searchCommentaires(@PathVariable Integer serieId, @PathVariable String query) {
        this.serieService.getSerieById(serieId);
        return this.commentaireService.searchCommentaires(query).toString();
    }

}
