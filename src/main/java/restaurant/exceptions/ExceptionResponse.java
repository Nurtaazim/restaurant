package restaurant.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    HttpStatus httpStatus;
    String exceptionClassName;
    String message;
}
