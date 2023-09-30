package amaroke.projet_cm.model.entity.key;

import java.io.Serializable;

import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BiblioJoinLivreId implements Serializable {

    private BiblioEntity biblio;

    private LivreEntity livre;

}
