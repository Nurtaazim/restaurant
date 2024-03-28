package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.StopListRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.PaginationResponse;
import restaurant.service.StopListService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stopList")
public class StopListAPI {
    private final StopListService service;
    @PostMapping("/{menuItemId}")
    MassageResponse save (@RequestBody StopListRequest stopListRequest,
                          @PathVariable long menuItemId){
      return   service.save(menuItemId, stopListRequest);
    }
    @GetMapping
    PaginationResponse getAll(@RequestParam int page,
                              @RequestParam int size){
        return service.getAll(page, size);
    }
    @DeleteMapping("/{id}")
    MassageResponse delete(@PathVariable long id){
        return service.delete(id);
    }

}
