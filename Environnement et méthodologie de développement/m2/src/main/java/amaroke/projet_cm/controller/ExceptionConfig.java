package amaroke.projet_cm.controller;

import amaroke.projet_cm.dto.ErrorMessageDto;
import amaroke.projet_cm.exceptions.LivreAlreadyExists;
import amaroke.projet_cm.exceptions.LivreNotFoundException;
import amaroke.projet_cm.exceptions.NullListException;
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

}