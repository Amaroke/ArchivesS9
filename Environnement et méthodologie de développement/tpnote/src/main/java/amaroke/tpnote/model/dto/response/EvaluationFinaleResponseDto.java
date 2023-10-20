package amaroke.tpnote.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import amaroke.tpnote.model.entity.EvaluationFinaleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvaluationFinaleResponseDto {

    public EvaluationFinaleResponseDto(EvaluationFinaleEntity evaluationFinaleEntity) {
        this.id = evaluationFinaleEntity.getId();
        this.decideurFinal = evaluationFinaleEntity.getDecideurFinal();
        this.note = evaluationFinaleEntity.getNote();
        this.qualitesDuRestaurant = evaluationFinaleEntity.getQualitesDuRestaurant();
        this.restaurant = new RestaurantResponseDto(evaluationFinaleEntity.getRestaurant());
    }

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("decideur_final")
    private String decideurFinal;

    @JsonProperty("note")
    private Integer note;

    @JsonProperty("qualites_du_restaurant")
    private String qualitesDuRestaurant;

    @JsonProperty("restaurant")
    private RestaurantResponseDto restaurant;
}
