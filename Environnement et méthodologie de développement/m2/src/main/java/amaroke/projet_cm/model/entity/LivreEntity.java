package amaroke.projet_cm.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivreEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_livre")
    private Integer id;

    @Column(name = "titre", columnDefinition = "varchar(255)", nullable = false)
    @JsonProperty("titre")
    private String titre;

    @ManyToMany(mappedBy = "livres")
    @JsonProperty("bibliotheques")
    List<BiblioJoinLivreEntity> biblioJoinLivreEntities;

}