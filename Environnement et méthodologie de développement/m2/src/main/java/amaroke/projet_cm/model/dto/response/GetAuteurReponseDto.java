package amaroke.projet_cm.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.entity.AuteurEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAuteurReponseDto {

    public GetAuteurReponseDto(AuteurEntity auteurEntity) {
        this.id = auteurEntity.getId();
        this.nom = auteurEntity.getNom();
        this.prenom = auteurEntity.getPrenom();
    }

    @JsonProperty("id")
    private String id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("prenom")
    private String prenom;

}
