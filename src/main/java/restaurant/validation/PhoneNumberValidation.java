package restaurant.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

import java.lang.annotation.*;

/**
 * @author Mukhammed Asantegin
 */
@Documented
@Constraint(
        validatedBy = PhoneNumberValidator.class
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValidation {
    String message() default "{Phone number must start with '+996' and length 13 symbol}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
