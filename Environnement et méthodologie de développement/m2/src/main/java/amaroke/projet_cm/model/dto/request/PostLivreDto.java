package amaroke.projet_cm.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostLivreDto {

    @JsonProperty("id")
    @Min(value = 0, message = "Id less than zero")
    private Integer id;

    @JsonProperty("titre")
    @Size(max = 100, message = "Title too long (more than 100 characters)")
    private String titre;

}
