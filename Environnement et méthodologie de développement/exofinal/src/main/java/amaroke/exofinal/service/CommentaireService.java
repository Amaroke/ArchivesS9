package amaroke.exofinal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.exofinal.model.entity.CommentaireEntity;
import amaroke.exofinal.repository.CommentaireRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentaireService {

    private final S3Service s3Service;
    private final IndexService indexService;
    private CommentaireRepository commentaireRepository;

    public String getScreenshot(Integer commentaireId) {
        return this.s3Service.getScreenshot(commentaireId);
    }

    public String addScreenshot(Integer commentaireId) {
        return this.s3Service.addScreenshot(commentaireId);
    }

    public List<CommentaireEntity> searchCommentaires(String query) {
        return this.indexService.searchDescription(query).stream()
                .map(commentaireId -> this.commentaireRepository.findById(Integer.valueOf(commentaireId)).orElseThrow())
                .toList();
    }
}
