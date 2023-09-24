package amaroke.projet_cm.controller;

import amaroke.projet_cm.exception.BiblioAlreadyExists;
import amaroke.projet_cm.exception.BiblioNotFoundException;
import amaroke.projet_cm.exception.CommentaireNotFound;
import amaroke.projet_cm.exception.LivreAlreadyExists;
import amaroke.projet_cm.exception.LivreNotFoundException;
import amaroke.projet_cm.exception.NullListException;
import amaroke.projet_cm.model.dto.ErrorMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto internalServerError(Exception e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("INTERNAL_ERROR", e.getMessage());
    }

    @ExceptionHandler(value = LivreNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto internalServerError(LivreNotFoundException e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(value = BiblioNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto internalServerError(BiblioNotFoundException e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(value = CommentaireNotFound.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto internalServerError(CommentaireNotFound e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(value = NullListException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ErrorMessageDto internalServerError(NullListException e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("NOT_FOUND", e.getMessage());
    }

    @ExceptionHandler(value = LivreAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto internalServerError(LivreAlreadyExists e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("BAD_REQUEST", e.getMessage());
    }

    @ExceptionHandler(value = BiblioAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto internalServerError(BiblioAlreadyExists e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("BAD_REQUEST", e.getMessage());
    }

}