package amaroke.projet_cm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import amaroke.projet_cm.exception.InvalidValueException;
import amaroke.projet_cm.exception.LivreNotFoundException;
import amaroke.projet_cm.model.entity.LivreEntity;
import amaroke.projet_cm.repository.LivreRepository;

@ExtendWith(MockitoExtension.class)
public class LivreServiceTest {

    @InjectMocks
    private LivreService livreService;

    @Mock
    private LivreRepository livreRepository;

    @Test
    void should_get_a_not_empty_list() {
        when(livreRepository.findAll()).thenReturn(List.of(LivreEntity.builder().titre("titre").build()));
        List<LivreEntity> result = livreService.getLivres();

        assertEquals(result.size(), 1);
    }

    @Test
    void updateLivreWithNullName() {
        assertThrows(InvalidValueException.class, () -> livreService.updateLivre(1, null));
    }

    @Test
    void updateLivreWithLivreNotFound() {
        when(livreRepository.findById(1)).thenReturn(java.util.Optional.empty());
        assertThrows(LivreNotFoundException.class, () -> livreService.updateLivre(1, "titre"));
    }

    @Test
    void updateLivreOk() {
        when(livreRepository.findById(1)).thenReturn(java.util.Optional.of(LivreEntity.builder().titre("titre").build()));
        livreService.updateLivre(1, "bonjour");
        assertEquals("bonjour", livreService.getLivre(1).getTitre());
        livreService.updateLivre(1, "aurevoir");
        assertEquals("aurevoir", livreService.getLivre(1).getTitre());
    }

}
