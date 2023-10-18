package amaroke.exofinal.model.dto.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CommentairePostDto {

    @JsonProperty("auteur")
    private String auteur;

    @JsonProperty("note")
    @Min(0)
    @Max(10)
    private Integer note;

    @JsonProperty("commentaire")
    private String commentaire;
}
