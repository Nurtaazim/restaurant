package restaurant.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
@Builder
public record MassageResponse(
        HttpStatus httpStatus,
        String massage
) {
}
