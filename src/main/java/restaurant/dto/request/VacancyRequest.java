package restaurant.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import restaurant.enums.Role;
import restaurant.repository.UserRepository;
import restaurant.validation.EmailValidation;
import restaurant.validation.PhoneNumberValidation;

import java.time.LocalDate;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Valid
public class VacancyRequest {
    private final UserRepository userRepository;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private LocalDate dateOfBirth;
    @EmailValidation
    private String email;
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    private int experience;
    @NotNull
    private String userName;
}
