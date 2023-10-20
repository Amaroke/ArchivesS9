package amaroke.tpnote.model.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_evaluation")
    private Integer id;

    @Column(name = "nom_evaluateur", columnDefinition = "varchar(50)", nullable = false)
    @JsonProperty("nom_evaluateur")
    private String nomEvaluateur;

    @Column(name = "commentaire", columnDefinition = "varchar(255)", nullable = false)
    @JsonProperty("commentaire")
    private String commentaire;

    @Column(name = "note", columnDefinition = "number", nullable = false)
    @JsonProperty("note")
    @Min(0)
    @Max(3)
    private Integer note;

    @Column(name = "date_creation", columnDefinition = "date", nullable = false)
    @JsonProperty("date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modification", columnDefinition = "date", nullable = false)
    @JsonProperty("date_modification")
    private LocalDate dateModification;

    @ManyToOne
    @JsonProperty("restaurant")
    private RestaurantEntity restaurant;
}
