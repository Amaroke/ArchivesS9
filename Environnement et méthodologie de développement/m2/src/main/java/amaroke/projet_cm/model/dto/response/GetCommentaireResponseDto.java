package amaroke.projet_cm.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.entity.CommentaireEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentaireResponseDto {

    public GetCommentaireResponseDto(CommentaireEntity commentaireEntity) {
        this.id = commentaireEntity.getId();
        this.commentaire = commentaireEntity.getCommentaire();
        this.livre = new GetLivreResponseDto(commentaireEntity.getLivre());
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("livre")
    GetLivreResponseDto livre;

}
