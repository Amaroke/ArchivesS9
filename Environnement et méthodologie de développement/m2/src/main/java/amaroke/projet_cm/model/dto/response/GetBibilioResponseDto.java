package amaroke.projet_cm.model.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.entity.BiblioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBibilioResponseDto {

    public GetBibilioResponseDto(BiblioEntity biblioEntity) {
        this.id = biblioEntity.getId();
        this.nom = biblioEntity.getNom();
        this.livres = biblioEntity.getBiblioJoinLivreEntities()
                .stream()
                .map(biblioJoinLivreEntity -> biblioJoinLivreEntity.getLivre().getId())
                .collect(Collectors.toList());

    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("livres")
    private List<Integer> livres;

}
