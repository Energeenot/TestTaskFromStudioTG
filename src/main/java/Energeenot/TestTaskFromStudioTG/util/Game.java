package Energeenot.TestTaskFromStudioTG.util;

import Energeenot.TestTaskFromStudioTG.dto.GameInfoResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Game {

    private static final Logger log = LoggerFactory.getLogger(Game.class);
    private final String gameId;
    private final int width;
    private final int height;
    private final int minesCount;
    private boolean completed;
    private GameState gameState;

    public Game(String gameId, int width, int height, int minesCount) {
        this.gameId = gameId;
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        this.completed = false;
        this.gameState = new GameState(width, height, minesCount, completed);
    }

    public GameInfoResponse makeTurn(int row, int col) {
        if (completed){
            log.info("Игра завершена");
            throw new IllegalStateException("Игра уже завершина"); //todo: подумать надо так или иначе
        }

        gameState.openCell(row, col);

        if (gameState.isCompleted()){
            completed = true;
            log.info("Игра завершена");
        }

        return GameInfoResponse.builder()
                .gameId(gameId)
                .width(width)
                .height(height)
                .minesCount(minesCount)
                .completed(completed)
                .field(gameState.getBoard())
                .build();
    }

}
