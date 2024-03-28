package restaurant.dto.request;
import lombok.Builder;

@Builder
public record UpdateRequest(
        String userName,
        String firstName,
        String lastName,
        String phoneNumber,
        int experience

) {
}
