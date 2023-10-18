package amaroke.exofinal.model.dto.request;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class SerieRequestPostDto {

    @JsonProperty("titre")
    private String titre;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date_sortie")
    private LocalDate dateSortie;

}
