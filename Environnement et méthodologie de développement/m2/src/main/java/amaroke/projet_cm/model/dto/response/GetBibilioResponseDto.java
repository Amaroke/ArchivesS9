package amaroke.projet_cm.model.dto.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.entity.BiblioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBibilioResponseDto {

    public GetBibilioResponseDto(BiblioEntity biblioModel) {
        this.id = biblioModel.getId();
        this.nom = biblioModel.getNom();
        this.livres = new ArrayList<GetLivreResponseDto>();
        biblioModel.getLivres().forEach(livre -> {
            this.livres.add(new GetLivreResponseDto(livre));
        });
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("livres")
    private ArrayList<GetLivreResponseDto> livres;

}
