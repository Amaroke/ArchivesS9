package amaroke.tpnote.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import amaroke.tpnote.model.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EvaluationResponseDto {

    public EvaluationResponseDto(EvaluationEntity evaluationEntity) {
        this.id = evaluationEntity.getId();
        this.nomEvaluateur = evaluationEntity.getNomEvaluateur();
        this.commentaire = evaluationEntity.getCommentaire();
        this.note = evaluationEntity.getNote();
        this.dateCreation = evaluationEntity.getDateCreation();
        this.dateModification = evaluationEntity.getDateModification();
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom_evaluateur")
    private String nomEvaluateur;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("date_creation")
    private LocalDate dateCreation;

    @JsonProperty("date_modification")
    private LocalDate dateModification;
}
