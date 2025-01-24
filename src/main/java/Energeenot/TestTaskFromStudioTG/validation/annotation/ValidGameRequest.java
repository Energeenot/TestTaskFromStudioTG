package Energeenot.TestTaskFromStudioTG.validation.annotation;

import Energeenot.TestTaskFromStudioTG.validation.validator.MinesCountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinesCountValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGameRequest {
    String message() default "Неверные параметры игры";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
