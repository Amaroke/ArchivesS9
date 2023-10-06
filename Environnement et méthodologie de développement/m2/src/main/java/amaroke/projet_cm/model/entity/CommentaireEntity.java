
package amaroke.projet_cm.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "commentaires")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_commentaire")
    private Integer id;

    @Column(name = "commentaire", columnDefinition = "varchar(2048)", nullable = false)
    @JsonProperty("commentaire")
    private String commentaire;

    @ManyToOne
    @JsonProperty("livre")
    LivreEntity livre;

}
