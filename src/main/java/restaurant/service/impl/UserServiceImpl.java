package restaurant.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant.dto.request.SaveRequest;
import restaurant.dto.request.SignInRequest;
import restaurant.dto.request.UpdateRequest;
import restaurant.dto.request.VacancyRequest;
import restaurant.dto.response.MassageResponse;
import restaurant.dto.response.UserResponse;
import restaurant.enums.Role;
import restaurant.exceptions.AlreadyExistException;
import restaurant.exceptions.BadRequestException;
import restaurant.exceptions.NotFoundException;
import restaurant.models.Restaurant;
import restaurant.models.User;
import restaurant.repository.RestaurantRepository;
import restaurant.repository.UserRepository;
import restaurant.sequrity.JwtService;
import restaurant.service.UserService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostConstruct
    public void saveAdmin(){
        Restaurant restaurant = new Restaurant("Nur", "Manas 11", "Chayhana", 0, 11);
        User user = new User("Nurtaazim", "Mukanov", LocalDate.of(2005, 1, 17), "nurtaazim@gmail.com", passwordEncoder.encode("1234"),
                "+996705170105", Role.ADMIN, 11, "admin");
        restaurant.getUsers().add(user);
        user.setRestaurant(restaurant);
        userRepository.save(user);
    }

    @Override
    public MassageResponse save(SaveRequest userRequest) {
        User user = new User(userRequest.firstName(), userRequest.lastName(), userRequest.dateOfBirth()
                , userRequest.email(),userRequest.password(), userRequest.phoneNumber(), userRequest.role()
                , userRequest.experience(), userRequest.userName());
        userRepository.save(user);
        confirm(user.getId());
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully saved!")
                .build();
    }



    @Override
    @Transactional
    public MassageResponse update(UpdateRequest userRequest) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(userName);  //.orElseThrow(()-> new NotFoundException("User with this name not found!"));
        user.setUserName(userRequest.userName());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setExperience(userRequest.experience());
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully updated!")
                .build();
    }

    @Override
    @Transactional
    public MassageResponse confirm(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found!"));
        Restaurant restaurant = restaurantRepository.getById();
        user.setRestaurant(restaurant);
        restaurantRepository.getById().getUsers().add(user);
        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Successfully added!").build();
    }

    @Override
    public UserResponse signIn(SignInRequest signInRequest) {
        User userByEmail = userRepository.getUserByEmail(signInRequest.getEmail());
        if (passwordEncoder.matches( signInRequest.getPassword(),userByEmail.getPassword())){
            return UserResponse.builder()
                    .token(jwtService.createToken(userByEmail))
                    .id(userByEmail.getId())
                    .email(userByEmail.getEmail())
                    .role(userByEmail.getRole())
                    .build();
        }
        else  throw new BadRequestException("Incorrect password!");

    }

    @Override
    public MassageResponse send(VacancyRequest vacancyRequest) {
        if (restaurantRepository.getUsersSize()>=15) throw new AlreadyExistException("No vacancies!");
        User user = new User();
        user.setRole(vacancyRequest.getRole());
        user.setFirstName(vacancyRequest.getFirstName());
        user.setLastName(vacancyRequest.getLastName());
        user.setPhoneNumber(vacancyRequest.getPhoneNumber());
        if (vacancyRequest.getRole().equals(Role.CHEF)) {
            if (vacancyRequest.getExperience() > 2) {
                user.setExperience(vacancyRequest.getExperience());
                if (LocalDate.now().getYear() - vacancyRequest.getDateOfBirth().getYear() > 25 && LocalDate.now().getYear() - vacancyRequest.getDateOfBirth().getYear() < 45) {
                    user.setDateOfBirth(vacancyRequest.getDateOfBirth());
                } else throw new BadRequestException("Your age must be between 25 and 45 !");
            } else throw new BadRequestException("Your experience must be at least 2 years.");
        }
        if (vacancyRequest.getRole().equals(Role.WAITER)) {
            if (vacancyRequest.getExperience() > 1) {
                user.setExperience(vacancyRequest.getExperience());
                if (LocalDate.now().getYear() - vacancyRequest.getDateOfBirth().getYear() > 18 && LocalDate.now().getYear() - vacancyRequest.getDateOfBirth().getYear() < 30) {
                    user.setDateOfBirth(vacancyRequest.getDateOfBirth());
                } else throw new BadRequestException("Your age must be between 18 and 30 !");
            } else throw new BadRequestException("Your experience must be at least 1 years.");
        }
        user.setEmail(vacancyRequest.getEmail());
        user.setPassword(passwordEncoder.encode(vacancyRequest.getPassword()));
        if (!userRepository.existsByUserName(vacancyRequest.getUserName())){
            user.setUserName(vacancyRequest.getUserName());
        }
        else throw new AlreadyExistException("A user with the same name already exists.");
        userRepository.save(user);

        return MassageResponse.builder()
                .httpStatus(HttpStatus.OK)
                .massage("Your request has been sent successfully!").build();
    }

    @Override
    public MassageResponse delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this id not found!"));
        userRepository.delete(user);
        return MassageResponse.builder()
                .massage("Successfully deleted!")
                .httpStatus(HttpStatus.OK).build();
    }
}
