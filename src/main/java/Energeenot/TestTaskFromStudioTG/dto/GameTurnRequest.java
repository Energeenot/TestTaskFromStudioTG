package Energeenot.TestTaskFromStudioTG.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameTurnRequest {

    @NotNull
    @JsonProperty("game_id")
    private String gameId;
    @Min(0)
    private int col;
    @Min(0)
    private int row;
}
