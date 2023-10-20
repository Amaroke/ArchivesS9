package amaroke.tpnote.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluationsfinales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_evaluation_finale")
    private Integer id;

    @Column(name = "decideur_final", columnDefinition = "varchar(90)", nullable = false)
    @JsonProperty("decideur_final")
    private String decideurFinal;

    @Column(name = "note", columnDefinition = "number", nullable = false)
    @JsonProperty("note")
    @Min(0)
    @Max(3)
    private Integer note;

    @Lob
    @Column(name = "qualites_du_restaurant", columnDefinition = "clob")
    @JsonProperty("qualites_du_restaurant")
    private String qualitesDuRestaurant;

    @ManyToOne
    @JsonProperty("restaurant")
    private RestaurantEntity restaurant;

}
