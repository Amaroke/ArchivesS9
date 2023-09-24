package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.CommentaireNotFound;
import amaroke.projet_cm.model.dto.request.PostCommentaireDto;
import amaroke.projet_cm.model.entity.CommentaireEntity;
import amaroke.projet_cm.repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final LivreService livreService;

    public List<CommentaireEntity> getCommentaires() {
        return commentaireRepository.findAll();
    }

    public CommentaireEntity getCommentaire(Integer commentaireId) {
        return commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new CommentaireNotFound("Commentaire with id " + commentaireId + " doesn't exist"));
    }

    public void addCommentaire(Integer livreId, PostCommentaireDto commentaire) {
        commentaireRepository
                .save(new CommentaireEntity(null, commentaire.getCommentaire(), livreService.getLivre(livreId)));
    }

    public void updateCommentaire(Integer commentaireId, String commentaire) {
        CommentaireEntity commentaireEntity = commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new CommentaireNotFound("Commentaire with id " + commentaireId + " doesn't exist"));
        commentaireEntity.setCommentaire(commentaire);
        commentaireRepository.save(commentaireEntity);
    }

    public void deleteCommentaire(Integer commentaireId) {
        commentaireRepository.deleteById(commentaireId);
    }

}
