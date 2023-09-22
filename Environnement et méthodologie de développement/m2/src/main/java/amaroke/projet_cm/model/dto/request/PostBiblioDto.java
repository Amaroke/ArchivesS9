package amaroke.projet_cm.model.dto.request;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostBiblioDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("livres")
    private ArrayList<PostLivreDto> livres;

}
