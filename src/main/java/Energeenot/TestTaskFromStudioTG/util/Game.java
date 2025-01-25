package Energeenot.TestTaskFromStudioTG.util;

import Energeenot.TestTaskFromStudioTG.dto.GameInfoResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Game {

    private final String gameId;
    private final int width;
    private final int height;
    private final int minesCount;
    private GameState gameState;

    public Game(String gameId, int width, int height, int minesCount) {
        this.gameId = gameId;
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        this.gameState = new GameState(width, height, minesCount);
    }

    public GameInfoResponse makeTurn(int row, int col) {
        log.info("Пользователь делает ход в ряд {} и столбец {}  в игре с ID {}", row, col, this.gameId);
        if (gameState.isCompleted()){
            log.info("Попытка сделать ход после завершения игры");
            throw new IllegalStateException("Игра завершена");
        }

        gameState.openCell(row, col);

        if (gameState.isCompleted()){
            log.info("Игра завершена");
        }

        return GameInfoResponse.builder()
                .gameId(gameId)
                .width(width)
                .height(height)
                .minesCount(minesCount)
                .completed(gameState.isCompleted())
                .field(gameState.getBoard())
                .build();
    }
}
