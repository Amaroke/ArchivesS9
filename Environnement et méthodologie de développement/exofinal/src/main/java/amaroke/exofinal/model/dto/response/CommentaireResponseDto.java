package amaroke.exofinal.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentaireResponseDto {

    public CommentaireResponseDto(amaroke.exofinal.model.entity.CommentaireEntity commentaireEntity) {
        this.auteur = commentaireEntity.getAuteur();
        this.note = commentaireEntity.getNote();
        this.commentaire = commentaireEntity.getCommentaire();
    }

    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("commentaire")
    private String commentaire;

}
