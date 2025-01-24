package Energeenot.TestTaskFromStudioTG.dto;

import Energeenot.TestTaskFromStudioTG.validation.annotation.ValidGameRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ValidGameRequest
public class NewGameRequest {

    @NotNull
    @Min(value = 2, message = "Значение должно быть больше или ровно 2.")
    @Max(value = 30, message = "Значение должно быть меньше или ровно 30.")
    private Integer width;

    @NotNull
    @Min(value = 2, message = "Значение должно быть больше или ровно 2.")
    @Max(value = 30, message = "Значение должно быть меньше или ровно 30.")
    private Integer height;

    @NotNull
    @Min(value = 1, message = "Значение должно быть больше или равно 1.")
    private Integer minesCount;
}
