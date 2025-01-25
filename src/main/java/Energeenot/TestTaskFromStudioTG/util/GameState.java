package Energeenot.TestTaskFromStudioTG.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Getter
@Slf4j
public class GameState {

    private static final int MINE = -1;
    private static final String EMPTY_CELL = " ";
    private static final String MINE_SYMBOL = "M";
    private static final String EXPLODED_MINE_SYMBOL = "X";

    private final int[][] field; // поле с отображёнными минами и числами
    private final String[][] board; // поле, которое видит пользователь
    private final boolean[][] openedCells; // Статус ячеек (открыты/закрыты)
    private final int width;
    private final int height;
    private boolean completed;
    private int openedCellsCount;
    private final int totalSafeCell;

    public GameState(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.field = new int[height][width];
        this.openedCells = new boolean[height][width];
        this.completed = false;
        this.board = new String[height][width];
        this.openedCellsCount = 0;
        this.totalSafeCell = width * height - minesCount;
        initializeBoard(width, height);
        placeMines(minesCount);
        calculateNumbers();
    }

    private void initializeBoard(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void placeMines(int minesCount) {
        log.info("Размещение мин");
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < minesCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            if (field[row][col] != MINE) {
                field[row][col] = MINE;
                placedMines++;
            }
        }
    }

    private void calculateNumbers() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (field[row][col] == MINE) {
                    incrementNeighbors(row, col);
                }
            }
        }
    }

    // Увеличиваем числа в соседних ячейках
    private void incrementNeighbors(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (isValidCell(newRow, newCol) && field[newRow][newCol] != MINE) {
                    field[newRow][newCol]++;
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public void openCell(int row, int col) {
        log.info("Проверка ячеек с координатами {} ряд {} столбец", row, col);
        if (!isValidCell(row, col) || completed) {
            log.error("{} ряд {} столбец неккоректны, либо игра окончена", row, col);
            throw new IllegalArgumentException("Невалидная ячейка, либо игра окончена");
        }

        if (openedCells[row][col]) {
            log.error("Ячейка с координатами {} {} открыта", row, col);
            throw new IllegalStateException("Уже открытая ячейка");
        }

        openedCells[row][col] = true;
        openedCellsCount++;

        if (field[row][col] == MINE) {
            log.info("Игра завершена. Пользователь наткнулся на мину");
            completed = true;
            revealAllBoard(true);
            return;
        }

        if (field[row][col] == 0) {
            // открываем соседние ячейки
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (isValidCell(newRow, newCol) && !openedCells[newRow][newCol]) {
                        openCell(newRow, newCol);
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (openedCells[i][j]) {
                    if (field[i][j] == MINE) {
                        continue;
                    }
                    board[i][j] = String.valueOf(field[i][j]);
                }
            }
        }

        if (openedCellsCount == totalSafeCell) {
            completed = true;
            log.info("Игра завершена победой, все безопасные ячейки открыты.");
            revealAllBoard(false);
        }
    }

    // когда пользователь взорвался explode = true, иначе false
    public void revealAllBoard(boolean explode) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = (field[i][j] == MINE)
                        ? (explode ? EXPLODED_MINE_SYMBOL : MINE_SYMBOL)
                        : String.valueOf(field[i][j]);
            }
        }
    }
}