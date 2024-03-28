package restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.request.UpdateRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserAPI {
    private final UserService userService;
    @PutMapping()
    MassageResponse update (@RequestBody UpdateRequest userRequest){
        return userService.update(userRequest);
    }

}
