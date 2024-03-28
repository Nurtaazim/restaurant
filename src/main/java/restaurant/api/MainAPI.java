package restaurant.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.dto.request.SignInRequest;
import restaurant.dto.request.VacancyRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.UserResponse;
import restaurant.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class MainAPI {
    private final UserService userService;
    @GetMapping
    UserResponse signIn (@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }
    @GetMapping("send")
    MassageResponse send (@RequestBody @Valid VacancyRequest vacancyRequest){
        return userService.send(vacancyRequest);
    }

}
