package restaurant.service;

import restaurant.dto.request.SaveRequest;
import restaurant.dto.request.SignInRequest;
import restaurant.dto.request.UpdateRequest;
import restaurant.dto.request.VacancyRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.UserResponse;

public interface UserService {
    MassageResponse save(SaveRequest userRequest);

    MassageResponse update(UpdateRequest userRequest);

    MassageResponse confirm(long id);

    UserResponse signIn(SignInRequest signInRequest);

    MassageResponse send(VacancyRequest vacancyRequest);

    MassageResponse delete(Long id);
}
