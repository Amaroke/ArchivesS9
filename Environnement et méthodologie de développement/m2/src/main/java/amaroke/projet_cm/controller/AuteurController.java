package amaroke.projet_cm.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amaroke.projet_cm.model.dto.response.GetAuteurReponseDto;
import amaroke.projet_cm.service.AuteurService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auteurs")
@Validated
@RequiredArgsConstructor
public class AuteurController {

    private final AuteurService auteurService;

    @GetMapping("")
    public List<GetAuteurReponseDto> getAuteurs() {
        return this.auteurService.getAuteurs().stream().map(GetAuteurReponseDto::new).toList();
    }
}
