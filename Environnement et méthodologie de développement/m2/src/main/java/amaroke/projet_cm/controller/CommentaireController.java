package amaroke.projet_cm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import amaroke.projet_cm.model.dto.request.PostCommentaireDto;
import amaroke.projet_cm.model.dto.response.GetCommentaireResponseDto;
import amaroke.projet_cm.service.CommentaireService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/commentaires")
@Validated
@RequiredArgsConstructor
public class CommentaireController {

    private final CommentaireService commentaireService;

    @GetMapping("")
    public @ResponseBody List<GetCommentaireResponseDto> getCommentaires() {
        return this.commentaireService.getCommentaires().stream().map(GetCommentaireResponseDto::new).toList();
    }

    @GetMapping("/{id}")
    public @ResponseBody GetCommentaireResponseDto getCommentaire(@PathVariable("id") @Min(0) Integer commentaireId) {
        return new GetCommentaireResponseDto(this.commentaireService.getCommentaire(commentaireId));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCommentaire(@PathVariable("id") @Min(0) Integer commentaireId,
            @RequestBody @Valid PostCommentaireDto commentaireDto) {
        this.commentaireService.updateCommentaire(commentaireId, commentaireDto.getCommentaire());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentaire(@PathVariable("id") @Min(0) Integer commentaireId) {
        this.commentaireService.deleteCommentaire(commentaireId);
    }

}
