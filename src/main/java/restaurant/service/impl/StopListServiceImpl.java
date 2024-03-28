package restaurant.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import restaurant.dto.request.StopListRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.PaginationResponse;
import restaurant.exceptions.NotFoundException;
import restaurant.models.MenuItem;
import restaurant.models.StopList;
import restaurant.repository.MenuItemRepository;
import restaurant.repository.StopListRepository;
import restaurant.service.StopListService;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public MassageResponse save(long menuItemId, StopListRequest stopListRequest) {
        StopList stopList = new StopList();
        stopList.setReason(stopListRequest.getReason());
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(() -> new NotFoundException("Menu item with this id not found!"));
        stopList.setMenuItem(menuItem);
        stopListRepository.save(stopList);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully saved!").build();
    }

    @Override
    public PaginationResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<StopList> all = stopListRepository.findAll(pageable);

        return PaginationResponse.builder()
                .page(all.getNumber()+1)
                .size(all.getTotalPages())
                .stopList(all.getContent()).build();
    }

    @Override
    public MassageResponse delete(long id) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(() -> new NotFoundException("Stop list with this id not found!"));
        stopListRepository.delete(stopList);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully deleted!").build();
    }

}
