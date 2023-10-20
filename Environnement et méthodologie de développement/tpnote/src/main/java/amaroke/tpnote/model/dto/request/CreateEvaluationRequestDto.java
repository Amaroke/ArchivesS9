package amaroke.tpnote.model.dto.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateEvaluationRequestDto {

    @JsonProperty("nom_evaluateur")
    private String nomEvaluateur;

    @JsonProperty("commentaire")
    private String commentaire;

    @JsonProperty("note")
    private Integer note;
}
