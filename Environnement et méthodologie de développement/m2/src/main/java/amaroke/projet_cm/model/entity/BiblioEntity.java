package amaroke.projet_cm.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "biblios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiblioEntity {

    @Id
    @GeneratedValue
    @JsonProperty("id_biblio")
    private Integer id;

    @JsonProperty("nom")
    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    private String nom;

    @ManyToMany
    @JoinTable(name = "biblio_jn_livre", joinColumns = @JoinColumn(name = "id_biblio"), inverseJoinColumns = @JoinColumn(name = "id_livre"))
    @JsonProperty("livres")
    List<LivreEntity> livres;

}
