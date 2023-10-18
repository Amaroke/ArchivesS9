package amaroke.exofinal.model.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.exofinal.model.entity.SerieEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerieResponseDto {

    public SerieResponseDto(SerieEntity serieEntity) {
        this.id = serieEntity.getId();
        this.titre = serieEntity.getTitre();
        this.description = serieEntity.getDescription();
        this.dateSortie = serieEntity.getDateSortie();
        this.commentaires = new ArrayList<CommentaireResponseDto>(
                serieEntity.getCommentaires().stream().map(CommentaireResponseDto::new).toList());
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titre")
    private String titre;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date_sortie")
    private LocalDate dateSortie;

    @JsonProperty("commentaires")
    private ArrayList<CommentaireResponseDto> commentaires;

}
