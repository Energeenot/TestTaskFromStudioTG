package Energeenot.TestTaskFromStudioTG.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameTurnRequest {

    @NotNull
    private String gameId;
    @Min(0)
    private int col;
    @Min(0)
    private int row;
}
