package Energeenot.TestTaskFromStudioTG.dto;

import Energeenot.TestTaskFromStudioTG.validation.annotation.ValidGameRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ValidGameRequest
public class NewGameRequest {

    @NotNull
    @Min(value = 2, message = "Значение должно быть больше или ровно 2.")
    @Max(value = 30, message = "Ширина поля должна быть не менее 2 и не более 30.")
    private Integer width;

    @NotNull
    @Min(value = 2, message = "Значение должно быть больше или ровно 2.")
    @Max(value = 30, message = "Высота поля должна быть не менее 2 и не более 30.")
    private Integer height;

    @NotNull
    @Min(value = 1, message = "Значение должно быть больше или равно 1.")
    @JsonProperty("mines_count")
    private Integer minesCount;
}
