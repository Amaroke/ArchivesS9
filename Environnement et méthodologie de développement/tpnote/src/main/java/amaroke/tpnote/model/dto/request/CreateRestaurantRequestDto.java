package amaroke.tpnote.model.dto.request;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.tpnote.model.enumeration.Tags;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateRestaurantRequestDto {

    @JsonProperty("nom")
    @Size(min = 1, max = 90)
    private String nom;

    @Size(min = 1, max = 255)
    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("tags")
    private List<Tags> tags;

}
