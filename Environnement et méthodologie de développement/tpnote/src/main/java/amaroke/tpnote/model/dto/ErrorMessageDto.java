package amaroke.tpnote.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    private String code;
    private String message;

}