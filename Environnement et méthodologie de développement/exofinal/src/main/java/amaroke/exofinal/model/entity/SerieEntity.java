package amaroke.exofinal.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "series")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerieEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_serie")
    private Integer id;

    @Column(name = "titre", columnDefinition = "varchar(255)", nullable = false)
    @JsonProperty("titre")
    private String titre;

    @Column(name = "description", columnDefinition = "varchar(255)", nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(name = "date_sortie", columnDefinition = "date", nullable = false)
    @JsonProperty("date_sortie")
    private LocalDate dateSortie;

    @OneToMany(mappedBy = "serie")
    @JsonProperty("commentaires")
    private List<CommentaireEntity> commentaires;

}
