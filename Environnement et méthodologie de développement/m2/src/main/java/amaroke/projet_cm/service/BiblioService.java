package amaroke.projet_cm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.exception.BiblioNotFoundException;
import amaroke.projet_cm.model.entity.BiblioEntity;
import amaroke.projet_cm.model.entity.BiblioJoinLivreEntity;
import amaroke.projet_cm.model.entity.key.BiblioJoinLivreId;
import amaroke.projet_cm.repository.BiblioJoinLivreRepository;
import amaroke.projet_cm.repository.BiblioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiblioService {

    private final BiblioRepository biblioRespository;
    private final LivreService livreService;
    private final BiblioJoinLivreRepository biblioJoinLivreRepository;
    private final S3Service s3Service;

    public List<BiblioEntity> getBiblios() {
        return this.biblioRespository.findAll();
    }

    public BiblioEntity getBiblio(Integer biblioId) {
        return this.biblioRespository
                .findById(biblioId)
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));
    }

    public void addBiblio(String biblioNom) {
        this.biblioRespository.save(BiblioEntity.builder().nom(biblioNom).build());
    }

    public void addLivreToBiblio(Integer biblioId, Integer livreId) {
        var biblio = this.getBiblio(biblioId);
        var livre = this.livreService.getLivre(livreId);
        var jn = BiblioJoinLivreEntity.builder().biblio(biblio).livre(livre).addDate(LocalDateTime.now()).build();

        this.biblioJoinLivreRepository.save(jn);
    }

    @Transactional
    public void deleteBiblio(int biblioId) {
        var biblio = this.getBiblio(biblioId);
        this.biblioJoinLivreRepository.deleteAllByBiblioIs(biblio);
        this.biblioRespository.delete(biblio);
    }

    public void deleteLivreFromBiblio(Integer biblioId, Integer livreId) {
        var biblio = this.getBiblio(biblioId);
        var livre = this.livreService.getLivre(livreId);

        var jointure = this.biblioJoinLivreRepository
                .findById(new BiblioJoinLivreId(biblio, livre))
                .orElseThrow(() -> new BiblioNotFoundException("Biblio with id " + biblioId + " doesn't exist"));

        this.biblioJoinLivreRepository.delete(jointure);
    }

    public String getCover(Integer biblioId) {
        return this.s3Service.getCoverBiblio(biblioId);
    }

    public String addCover(Integer biblioId) {
        return this.s3Service.addCoverBiblio(biblioId);
    }

}
