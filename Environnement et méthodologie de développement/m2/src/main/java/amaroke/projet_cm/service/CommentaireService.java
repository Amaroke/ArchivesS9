package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.CommentaireNotFound;
import amaroke.projet_cm.model.entity.CommentaireEntity;
import amaroke.projet_cm.repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;

    public List<CommentaireEntity> getCommentaires() {
        return this.commentaireRepository.findAll();
    }

    public CommentaireEntity getCommentaire(Integer commentaireId) {
        return this.commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new CommentaireNotFound("Commentaire with id " + commentaireId + " doesn't exist"));
    }

    public void updateCommentaire(Integer commentaireId, String commentaire) {
        CommentaireEntity commentaireEntity = this.commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new CommentaireNotFound("Commentaire with id " + commentaireId + " doesn't exist"));
        commentaireEntity.setCommentaire(commentaire);
        this.commentaireRepository.save(commentaireEntity);
    }

    public void deleteCommentaire(Integer commentaireId) {
        this.commentaireRepository.deleteById(commentaireId);
    }

}
