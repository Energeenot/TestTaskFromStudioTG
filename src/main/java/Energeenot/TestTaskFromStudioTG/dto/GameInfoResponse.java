package Energeenot.TestTaskFromStudioTG.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class GameInfoResponse {

    @JsonProperty("game_id")
    private String gameId;
    private int width;
    private int height;
    @JsonProperty("mines_count")
    private int minesCount;
    private boolean completed;
    private String[][] field;
}
