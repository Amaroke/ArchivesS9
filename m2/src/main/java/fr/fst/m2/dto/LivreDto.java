package fr.fst.m2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivreDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("titre")
    private String titre;

}