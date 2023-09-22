package amaroke.projet_cm.model.dto.service;

import java.util.ArrayList;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Validated
public class BiblioModel {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("livres")
    private ArrayList<LivreModel> livres;

}
