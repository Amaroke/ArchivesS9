package amaroke.projet_cm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import amaroke.projet_cm.repository.BiblioJoinLivreRepository;
import amaroke.projet_cm.repository.CommentaireRepository;

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

    @Mock
    private BiblioJoinLivreRepository biblioJoinLivreRepository;

    @Mock
    private CommentaireRepository commentaireRepository;

    @Test
    void getLivres() {
        when(livreRepository.findAll()).thenReturn(List.of(LivreEntity.builder().titre("titre").build()));

        List<LivreEntity> result = livreService.getLivres();

        assertEquals(result.size(), 1);
    }

    @Test
    void getLivreExisting() {
        var livre = LivreEntity.builder().titre("titre").build();

        when(livreRepository.findById(1)).thenReturn(Optional.of(livre));

        LivreEntity result = livreService.getLivre(1);

        assertEquals(result, livre);
    }

    @Test 
    void getLivreNotFound() {
        when(livreRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(LivreNotFoundException.class, () -> livreService.getLivre(1));
    }

    @Test
    void addLivre() {
        LivreEntity livreEntityToAdd = LivreEntity.builder().titre("titre").build();

        when(livreRepository.save(any(LivreEntity.class))).thenReturn(livreEntityToAdd);

        livreService.addLivre("titre");

        verify(livreRepository, times(1)).save(argThat(entity -> entity.getTitre().equals("titre")));
    }

    @Test
    void updateLivreNullName() {
        assertThrows(InvalidValueException.class, () -> livreService.updateLivre(1, null));
    }

    @Test
    void updateLivreNotFound() {
        when(livreRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(LivreNotFoundException.class, () -> livreService.updateLivre(1, "titre"));
    }

    @Test
    void updateLivreOk() {
        when(livreRepository.findById(1)).thenReturn(Optional.of(LivreEntity.builder().titre("titre").build()));

        livreService.updateLivre(1, "bonjour");

        assertEquals("bonjour", livreService.getLivre(1).getTitre());
    }

    @Test
    void deleteLivre() {
        when(livreRepository.findById(1)).thenReturn(Optional.of(LivreEntity.builder().id(1).titre("titre").build()));
        livreService.deleteLivre(1);

        verify(biblioJoinLivreRepository, times(1)).deleteAllByLivreIs(LivreEntity.builder().id(1).titre("titre").build());
        verify(livreRepository, times(1)).delete(LivreEntity.builder().id(1).titre("titre").build());
    }

    @Test
    void addCommentaire() {
        when(livreRepository.findById(1)).thenReturn(Optional.of(LivreEntity.builder().id(1).titre("titre").build()));

        livreService.addCommentaire(1, "commentaire");
        
        verify(commentaireRepository, times(1))
                .save(argThat(entity -> entity.getCommentaire().equals("commentaire") && entity.getLivre().getId().equals(1)));
    }
}
