package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.InvalidValueException;
import amaroke.projet_cm.exception.LivreNotFoundException;
import amaroke.projet_cm.model.entity.CommentaireEntity;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.repository.BiblioJoinLivreRepository;
import amaroke.projet_cm.repository.CommentaireRepository;
import amaroke.projet_cm.repository.LivreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final LivreRepository livreRepository;
    private final CommentaireRepository commentaireRepository;
    private final BiblioJoinLivreRepository biblioJoinLivreRepository;
    private final S3Service s3Service;

    public List<LivreEntity> getLivres() {
        return this.livreRepository.findAll();
    }

    public LivreEntity getLivre(Integer id) {
        return this.livreRepository.findById(id)
                .orElseThrow(() -> new LivreNotFoundException("Livre with id " + id + " doesn't exist"));
    }

    public void addLivre(String livreName) {
        this.livreRepository.save(LivreEntity.builder().titre(livreName).build());
    }

    public void updateLivre(Integer livreId, String titre) {
        if (titre == null) {
            throw new InvalidValueException("le nouveau nom ne doit pas être null");
        }
        final LivreEntity livreToUpdate = this.livreRepository.findById(livreId)
                .orElseThrow(() -> new LivreNotFoundException("le livre d'id " + livreId + " n'existe pas"));
        livreToUpdate.setTitre(titre);
        livreRepository.save(livreToUpdate);
    }

    @Transactional
    public void deleteLivre(int id) {
        var livre = this.getLivre(id);
        this.biblioJoinLivreRepository.deleteAllByLivreIs(livre);
        this.livreRepository.delete(livre);
    }

    public void addCommentaire(Integer livreId, String commentaire) {
        this.commentaireRepository
                .save(new CommentaireEntity(null, commentaire, this.getLivre(livreId)));
    }

    public String getCover(Integer livreId) {
        return this.s3Service.getCover(livreId);
    }

    public String addCover(Integer livreId) {
        return this.s3Service.addCover(livreId);
    }

}