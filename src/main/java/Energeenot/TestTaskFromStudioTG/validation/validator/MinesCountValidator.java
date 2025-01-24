package Energeenot.TestTaskFromStudioTG.validation.validator;

import Energeenot.TestTaskFromStudioTG.dto.NewGameRequest;
import Energeenot.TestTaskFromStudioTG.validation.annotation.ValidGameRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinesCountValidator implements ConstraintValidator<ValidGameRequest, NewGameRequest> {

    @Override
    public boolean isValid(NewGameRequest newGameRequest, ConstraintValidatorContext context) {
        if (newGameRequest == null) return false;

        int maxMines = newGameRequest.getWidth() * newGameRequest.getHeight() - 1;
        int minMines = 1;

        if (newGameRequest.getMinesCount() < minMines || newGameRequest.getMinesCount() > maxMines) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("Количество мин должно быть не менее 1 и не более %d",
                            maxMines)
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
