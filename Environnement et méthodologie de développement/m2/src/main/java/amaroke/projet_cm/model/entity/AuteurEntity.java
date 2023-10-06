package amaroke.projet_cm.model.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auteurs")
@NoArgsConstructor
@Data
public class AuteurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nom", columnDefinition = "varchar(255)", nullable = false)
    private String nom;

    @Column(name = "prenom", columnDefinition = "varchar(255)", nullable = false)
    private String prenom;

    @ManyToMany
    @JoinTable(name = "livres_jn_auteurs", joinColumns = @JoinColumn(name = "id_livre"), inverseJoinColumns = @JoinColumn(name = "id_auteur"))
    private Set<LivreEntity> livres;
}
