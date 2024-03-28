package restaurant.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import restaurant.repository.UserRepository;

@RequiredArgsConstructor

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        return email.endsWith("@gmail.com") || !userRepository.existsByEmail(email);
    }
}
