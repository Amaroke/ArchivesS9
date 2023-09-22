package amaroke.projet_cm.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAddLivreToBiblioRequestDto {
    @JsonProperty("id")
    @Min(value = 0, message = "Id less than zero")
    private Integer id;
}
