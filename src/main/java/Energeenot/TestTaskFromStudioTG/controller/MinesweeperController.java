package Energeenot.TestTaskFromStudioTG.controller;

import Energeenot.TestTaskFromStudioTG.dto.GameTurnRequest;
import Energeenot.TestTaskFromStudioTG.dto.NewGameRequest;
import Energeenot.TestTaskFromStudioTG.dto.GameInfoResponse;
import Energeenot.TestTaskFromStudioTG.util.Game;
import Energeenot.TestTaskFromStudioTG.service.GameService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minesweeper")
@Slf4j
public class MinesweeperController {

    private final GameService gameService;

    @Autowired
    public MinesweeperController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public ResponseEntity<GameInfoResponse> createNewGame(@RequestBody @Valid NewGameRequest newGameRequest) {
        log.info("Создание новой игры, с широной {}, с высотой {}, с {} минами", newGameRequest.getWidth(), newGameRequest.getHeight(), newGameRequest.getMinesCount());
        GameInfoResponse response = gameService.createNewGame(newGameRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/turn")
    public ResponseEntity<GameInfoResponse> makeTurn(@RequestBody @Valid GameTurnRequest gameTurnRequest) {
        log.info("Делается ход в игре с ID {} на {} ряд и на {} столбец", gameTurnRequest.getGameId(), gameTurnRequest.getRow(), gameTurnRequest.getCol());
        Game game = gameService.findGameById(gameTurnRequest.getGameId());
        GameInfoResponse response = game.makeTurn(gameTurnRequest.getRow(), gameTurnRequest.getCol());
        return ResponseEntity.ok(response);
    }
}
