package restaurant.dto.response;

import lombok.Builder;
import restaurant.models.StopList;

import java.util.List;
@Builder
public record PaginationResponse(
        int page,
        int size,
        List<StopList> stopList
) {
}
