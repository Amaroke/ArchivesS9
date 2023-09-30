package amaroke.projet_cm.model.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.projet_cm.model.entity.LivreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetLivreResponseDto {

    public GetLivreResponseDto(LivreEntity livreDto) {
        this.id = livreDto.getId();
        this.titre = livreDto.getTitre();
        this.bibliotheques = livreDto.getBiblioJoinLivreEntities()
                .stream()
                .map(biblioJoinLivreEntity -> biblioJoinLivreEntity.getBiblio().getId())
                .collect(Collectors.toList());
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titre")
    private String titre;

    @JsonProperty("bibliotheques")
    List<Integer> bibliotheques = new ArrayList<>();
}
