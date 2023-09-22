package amaroke.projet_cm.model.dto.service;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Validated
public class LivreModel {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titre")
    private String titre;

}