package Energeenot.TestTaskFromStudioTG.dto;

import lombok.*;

@Data
@Builder
public class GameInfoResponse {

    private String gameId;
    private int width;
    private int height;
    private int minesCount;
    private boolean completed;
    private String[][] field;
}
