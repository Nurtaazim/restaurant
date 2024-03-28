package restaurant.service;

import restaurant.dto.request.StopListRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.PaginationResponse;

public interface StopListService {
    MassageResponse save(long menuItemId, StopListRequest stopListRequest);

    PaginationResponse getAll(int page, int size);

    MassageResponse delete(long id);
}
