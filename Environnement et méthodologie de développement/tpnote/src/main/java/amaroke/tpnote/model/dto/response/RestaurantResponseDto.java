package amaroke.tpnote.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import amaroke.tpnote.model.entity.RestaurantEntity;
import amaroke.tpnote.model.enumeration.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantResponseDto {

    public RestaurantResponseDto(RestaurantEntity restaurantEntity) {
        this.id = restaurantEntity.getId();
        this.nom = restaurantEntity.getNom();
        this.adresse = restaurantEntity.getAdresse();
        this.moyenne = restaurantEntity.getMoyenne();
        this.tags = restaurantEntity.getTags();
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("moyenne")
    private Float moyenne;

    @JsonProperty("tags")
    private List<Tags> tags;
}
