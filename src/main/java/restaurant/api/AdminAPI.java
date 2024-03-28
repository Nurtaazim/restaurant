package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.SaveRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAPI {
    private final UserService userService;
    @PostMapping
    MassageResponse save (@RequestBody SaveRequest userRequest){
        return userService.save(userRequest);
    }
    @PutMapping("/confirm/{id}")
    MassageResponse confirm(@PathVariable long id){
        return userService.confirm(id);
    }
    @DeleteMapping("/{id}")
    MassageResponse delete(@PathVariable Long id){
        return userService.delete(id);
    }
    @DeleteMapping("/reject/{id}")
    MassageResponse reject(@PathVariable long id){
        return userService.delete(id);
    }
}
