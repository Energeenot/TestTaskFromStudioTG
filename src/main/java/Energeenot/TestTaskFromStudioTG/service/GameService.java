package Energeenot.TestTaskFromStudioTG.service;

import Energeenot.TestTaskFromStudioTG.dto.GameInfoResponse;
import Energeenot.TestTaskFromStudioTG.dto.NewGameRequest;
import Energeenot.TestTaskFromStudioTG.util.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class GameService {

    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    public GameInfoResponse createNewGame(NewGameRequest newGameRequest) {
        String gameId = UUID.randomUUID().toString();

        Game game = new Game(gameId, newGameRequest.getWidth(), newGameRequest.getHeight(), newGameRequest.getMinesCount());
        games.put(gameId, game);
        log.info("Создана игра с ID {}", gameId);
        return GameInfoResponse.builder()
                .gameId(gameId)
                .width(newGameRequest.getWidth())
                .height(newGameRequest.getHeight())
                .minesCount(newGameRequest.getMinesCount())
                .completed(false)
                .field(game.getGameState().getBoard())
                .build();

    }

    public Game findGameById(String gameId) {
        Game game = games.get(gameId);
        if (game == null) {
            log.error("Игра с ID {} не найдена", gameId);
            throw new IllegalArgumentException("Игра с ID " + gameId + " не найдена");
        }
        log.info("Ига с ID {} найдена", gameId);
        log.info("У игры с ID {} {} мин, {} ширина, {} высота", gameId, game.getMinesCount(), game.getWidth(), game.getHeight());
        return game;
    }
}
