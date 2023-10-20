package amaroke.tpnote.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import amaroke.tpnote.model.dto.ErrorMessageDto;

@ControllerAdvice
@Slf4j
public class ExceptionConfig {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageDto internalServerError(NotFoundException e) {
        log.error(e.getMessage());
        return new ErrorMessageDto("NOT_FOUND", e.getMessage());
    }

}