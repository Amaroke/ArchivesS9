package amaroke.projet_cm.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Validated
public class LivreDto {

    @JsonProperty("id")
    @Min(value = 0, message = "Id less than zero")
    private Integer id;

    @JsonProperty("titre")
    @Size(max = 10, message = "Title too long (more than 10 characters)")
    private String titre;

}