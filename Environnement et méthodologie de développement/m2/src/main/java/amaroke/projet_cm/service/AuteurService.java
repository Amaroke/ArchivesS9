package amaroke.projet_cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import amaroke.projet_cm.model.entity.AuteurEntity;
import amaroke.projet_cm.repository.AuteurRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuteurService {

    private final AuteurRepository auteurRepository;

    public List<AuteurEntity> getAuteurs() {
        return this.auteurRepository.findAll();
    }

}
