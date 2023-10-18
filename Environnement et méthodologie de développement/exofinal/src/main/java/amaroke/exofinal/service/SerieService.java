package amaroke.exofinal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.exofinal.model.entity.CommentaireEntity;
import amaroke.exofinal.model.entity.SerieEntity;
import amaroke.exofinal.repository.CommentaireRepository;
import amaroke.exofinal.repository.SerieRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;
    private final CommentaireRepository commentaireRepository;
    private final S3Service s3Service;
    private final IndexService indexService;

    public List<SerieEntity> getAllSeries() {
        return serieRepository.findAll();
    }

    public SerieEntity getSerieById(Integer id) {
        return serieRepository.findById(id).orElseThrow();
    }

    public void addSerie(String titre, String description, LocalDate dateSortie) {
        SerieEntity serieEntity = this.serieRepository.save(SerieEntity.builder()
                .titre(titre)
                .description(description)
                .dateSortie(dateSortie).build());
        this.indexService.indexDescription(serieEntity.getDescription(), serieEntity.getId());
    }

    public void deleteSerieById(Integer id) {
        serieRepository.deleteById(id);
    }

    public void addCommentaire(Integer serieId, String auteur, Integer note, String commentaire) {
        CommentaireEntity commentaireEntity = commentaireRepository.save(CommentaireEntity.builder()
                .auteur(auteur)
                .note(note)
                .commentaire(commentaire)
                .serie(serieRepository.findById(serieId).orElseThrow()).build());
        this.indexService.indexCommentaire(commentaireEntity.getCommentaire(), commentaireEntity.getId());
    }

    public String getCover(Integer livreId) {
        return this.s3Service.getCover(livreId);
    }

    public String addCover(Integer livreId) {
        return this.s3Service.addCover(livreId);
    }

    public List<SerieEntity> searchSeries(String query) {
        return this.indexService.searchDescription(query).stream()
                .map(serieId -> this.serieRepository.findById(Integer.valueOf(serieId)).orElseThrow())
                .toList();
    }

}
