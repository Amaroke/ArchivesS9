package amaroke.projet_cm.model.dto.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PostLivreDto {

    @JsonProperty("id")
    @Min(value = 0, message = "Id less than zero")
    private Integer id;

    @JsonProperty("titre")
    @Size(max = 255, message = "Title too long (more than 255 characters)")
    private String titre;

}
