package Energeenot.TestTaskFromStudioTG.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStateTest {

    private GameState gameState;

    @BeforeEach
    void setUp(){
        gameState = new GameState(5, 5, 3);
    }

    @Test
    void placeMinesShouldPlaceCorrectNumberOfMines() {
        int mineCount = 0;
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getField()[i][j] == -1){
                    mineCount++;
                }
            }
        }
        assertThat(mineCount).isEqualTo(3);
    }

    @Test
    void openCellShouldCompleteGameThanMineExplode(){
        int mineRow = 0;
        int mineCol = 0;

        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getField()[i][j] == -1){
                    mineRow = i;
                    mineCol = j;
                    break;
                }
            }
        }

        gameState.openCell(mineRow, mineCol);
        assertThat(gameState.isCompleted()).isTrue();
        assertThat(gameState.getBoard()[mineRow][mineCol]).isEqualTo("X");
    }

    @Test
    void openCellShouldCompleteGameThanAllSafeCellsOpened(){
        for (int i = 0; i < gameState.getHeight(); i++) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getField()[i][j] != -1 && !gameState.getOpenedCells()[i][j]){
                    gameState.openCell(i, j);
                }
            }
        }

        assertThat(gameState.isCompleted()).isTrue();
    }
}
