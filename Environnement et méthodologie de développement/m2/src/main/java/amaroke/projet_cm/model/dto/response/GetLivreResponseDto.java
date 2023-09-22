package amaroke.projet_cm.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.dto.service.LivreModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetLivreResponseDto {

    public GetLivreResponseDto(LivreModel livreDto) {
        this.id = livreDto.getId();
        this.titre = livreDto.getTitre();
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titre")
    private String titre;
}
