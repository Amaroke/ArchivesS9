package amaroke.tpnote.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import amaroke.tpnote.model.dto.request.CreateEvaluationRequestDto;
import amaroke.tpnote.model.dto.request.CreateRestaurantRequestDto;
import amaroke.tpnote.model.dto.response.EvaluationResponseDto;
import amaroke.tpnote.model.dto.response.RestaurantResponseDto;
import amaroke.tpnote.service.EvaluationService;
import amaroke.tpnote.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/restaurants")
@Validated
@RequiredArgsConstructor
public class RestaurantController {

        private final RestaurantService restaurantService;
        private final EvaluationService evaluationService;

        @GetMapping("")
        @Operation(description = "Obtenir la liste de tous les restaurants")
        public List<RestaurantResponseDto> getAllRestaurants() {
                return this.restaurantService.getAllRestaurants().stream()
                                .map(RestaurantResponseDto::new)
                                .toList();
        }

        @GetMapping("/search")
        @Operation(description = "Rechercher des restaurants en fonction des critères spécifiés")
        public List<RestaurantResponseDto> searchRestaurants(
                        @RequestParam(name = "keyword", required = false) @Param("Mot-clé de recherche") String keyword,
                        @RequestParam(name = "minNote", required = false) @Param("Note minimale") Float minNote,
                        @RequestParam(name = "maxNote", required = false) @Param("Note maximale") Float maxNote,
                        @RequestParam(name = "tag", required = false) @Param("Tag associé") String tag) {

                return this.restaurantService.searchRestaurants(keyword, minNote, maxNote, tag).stream()
                                .map(RestaurantResponseDto::new)
                                .toList();
        }

        @PostMapping("")
        @Operation(description = "Créer un nouveau restaurant")
        @ResponseStatus(HttpStatus.CREATED)
        public void createRestaurant(@RequestBody @Valid CreateRestaurantRequestDto createRestaurantRequest) {
                this.restaurantService.createRestaurant(createRestaurantRequest.getNom(),
                                createRestaurantRequest.getAdresse(),
                                createRestaurantRequest.getTags());
        }

        @PutMapping("/{idRestaurant}")
        @Operation(description = "Mettre à jour les informations d'un restaurant")
        @ResponseStatus(HttpStatus.OK)
        public void updateRestaurant(@PathVariable Integer idRestaurant,
                        @RequestBody @Valid CreateRestaurantRequestDto createRestaurantRequest) {
                this.restaurantService.updateRestaurant(idRestaurant, createRestaurantRequest.getNom(),
                                createRestaurantRequest.getAdresse(),
                                createRestaurantRequest.getTags());
        }

        @PostMapping("/{idRestaurant}/evaluations")
        @Operation(description = "Créer une nouvelle évaluation pour un restaurant")
        @ResponseStatus(HttpStatus.CREATED)
        public void createEvaluation(@PathVariable Integer idRestaurant,
                        @RequestBody @Valid CreateEvaluationRequestDto createEvaluationRequest) {
                this.evaluationService.createEvaluation(idRestaurant, createEvaluationRequest.getNomEvaluateur(),
                                createEvaluationRequest.getCommentaire(), createEvaluationRequest.getNote());
        }

        @PutMapping("/evaluations/{idEvaluation}")
        @Operation(description = "Mettre à jour les informations d'une évaluation")
        @ResponseStatus(HttpStatus.OK)
        public void updateEvaluation(@PathVariable Integer idEvaluation,
                        @RequestBody @Valid CreateEvaluationRequestDto createEvaluationRequest) {
                this.evaluationService.updateEvaluation(idEvaluation, createEvaluationRequest.getNomEvaluateur(),
                                createEvaluationRequest.getCommentaire(), createEvaluationRequest.getNote());
        }

        @GetMapping("/{idRestaurant}/evaluations")
        @Operation(description = "Obtenir les évaluations pour un restaurant, il faut mettre \"sortby=note\" pour trier par note")
        @ResponseStatus(HttpStatus.OK)
        public List<EvaluationResponseDto> getEvaluations(@PathVariable Integer idRestaurant,
                        @RequestParam(required = false) String sortBy) {
                List<EvaluationResponseDto> evaluations = new ArrayList<>(
                                this.evaluationService.getEvaluations(idRestaurant));

                if (sortBy != null && sortBy.equalsIgnoreCase("note")) {
                        evaluations.sort(Comparator.comparing(EvaluationResponseDto::getNote));
                }

                return evaluations;
        }

        @GetMapping("/{restaurantId}/image")
        public String getCover(@PathVariable Integer restaurantId) {
                return this.restaurantService.getImage(restaurantId);
        }

        @PutMapping("/{restaurantId}/image")
        public String addCover(@PathVariable Integer restaurantId) {
                return this.restaurantService.addImage(restaurantId);
        }
}
