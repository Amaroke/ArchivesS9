package amaroke.projet_cm.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.repository.BiblioRepository;
import amaroke.projet_cm.repository.LivreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class DataInitializer implements CommandLineRunner {

    private final BiblioRepository biblioRepository;
    private final LivreRepository livreRepository;
    private final boolean chargerDesExemples = false;

    @Override
    public void run(String... args) throws Exception {
        if (chargerDesExemples) {
            BiblioEntity biblio1 = new BiblioEntity();
            biblio1.setNom("Bibliothèque 1");

            BiblioEntity biblio2 = new BiblioEntity();
            biblio2.setNom("Bibliothèque 2");

            LivreEntity livre1 = new LivreEntity();
            livre1.setTitre("Livre 1");
            livre1.getBibliotheques().add(biblio1);
            biblio1.getLivres().add(livre1);

            LivreEntity livre2 = new LivreEntity();
            livre2.setTitre("Livre 2");
            livre2.getBibliotheques().add(biblio1);
            biblio1.getLivres().add(livre2);

            LivreEntity livre3 = new LivreEntity();
            livre3.setTitre("Livre 3");
            livre3.getBibliotheques().add(biblio2);
            biblio2.getLivres().add(livre3);

            livreRepository.save(livre1);
            livreRepository.save(livre2);
            livreRepository.save(livre3);
            biblioRepository.save(biblio1);
            biblioRepository.save(biblio2);

            System.out.println("Exemples de données ajoutés à la base de données.");
        }
    }
}
