package amaroke.tpnote.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import amaroke.tpnote.model.enumeration.Tags;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_restaurant")
    private Integer id;

    @Column(name = "nom", columnDefinition = "varchar(90)", nullable = false)
    @JsonProperty("nom")
    private String nom;

    @Column(name = "adresse", columnDefinition = "varchar(255)", nullable = false)
    @JsonProperty("adresse")
    private String adresse;

    @OneToMany(mappedBy = "restaurant")
    @JsonProperty("evaluations")
    private List<EvaluationEntity> evaluations;

    @Column(name = "moyenne", columnDefinition = "float", nullable = true)
    @JsonProperty("moyenne")
    private Float moyenne;

    @OneToOne(mappedBy = "restaurant")
    @JsonProperty("evaluation_finale")
    private EvaluationFinaleEntity evaluationFinale;

    @ElementCollection
    @JsonProperty("tags")
    private List<Tags> tags;

}
