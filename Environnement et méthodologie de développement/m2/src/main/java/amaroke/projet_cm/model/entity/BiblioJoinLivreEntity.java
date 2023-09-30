package amaroke.projet_cm.model.entity;

import java.time.LocalDateTime;

import amaroke.projet_cm.model.entity.key.BiblioJoinLivreId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Biblios_jn_livres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BiblioJoinLivreId.class)
public class BiblioJoinLivreEntity {

    @Id
    @ManyToOne
    private LivreEntity livre;

    @Id
    @ManyToOne
    private BiblioEntity biblio;

    @Column(name = "add_date")
    private LocalDateTime addDate;

}
