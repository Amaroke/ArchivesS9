package amaroke.tpnote.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.tpnote.model.dto.response.EvaluationResponseDto;
import amaroke.tpnote.model.entity.EvaluationEntity;
import amaroke.tpnote.repository.EvaluationRepository;
import amaroke.tpnote.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final RestaurantRepository restaurantRepository;

    public void createEvaluation(Integer idRestaurant, String nomEvaluateur, String commentaire, Integer note) {
        this.evaluationRepository.save(EvaluationEntity.builder()
                .nomEvaluateur(nomEvaluateur)
                .commentaire(commentaire)
                .note(note)
                .dateCreation(LocalDate.now())
                .dateModification(LocalDate.now())
                .restaurant(this.restaurantRepository.findById(idRestaurant).orElseThrow())
                .build());
    }

    public void updateEvaluation(Integer idEvaluations, String nomEvaluateur, String commentaire, Integer note) {
        final EvaluationEntity evaluationEntityToUpdate = this.evaluationRepository.findById(idEvaluations)
                .orElseThrow();
        evaluationEntityToUpdate.setNomEvaluateur(nomEvaluateur);
        evaluationEntityToUpdate.setCommentaire(commentaire);
        evaluationEntityToUpdate.setNote(note);
        evaluationEntityToUpdate.setDateModification(LocalDate.now());
        evaluationRepository.save(evaluationEntityToUpdate);
    }

    public List<EvaluationResponseDto> getEvaluations(Integer idRestaurant) {
        return this.restaurantRepository.findById(idRestaurant).orElseThrow().getEvaluations().stream()
                .map(EvaluationResponseDto::new)
                .toList();
    }

}
