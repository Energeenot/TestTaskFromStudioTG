package Energeenot.TestTaskFromStudioTG.controller;

import Energeenot.TestTaskFromStudioTG.dto.GameInfoResponse;
import Energeenot.TestTaskFromStudioTG.dto.GameTurnRequest;
import Energeenot.TestTaskFromStudioTG.dto.NewGameRequest;
import Energeenot.TestTaskFromStudioTG.service.GameService;
import Energeenot.TestTaskFromStudioTG.util.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MinesweeperController.class)
public class MinesweeperControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private GameService gameService;
    private GameInfoResponse expectedResponse;


    @BeforeEach
    void setUp() {
        expectedResponse = GameInfoResponse.builder()
                .gameId("game-id")
                .field(new String[10][10])
                .build();
    }

    @Test
    void createNewGameShouldReturnCorrectGameInfoResponse() throws Exception {
        NewGameRequest request = new NewGameRequest();
        request.setWidth(10);
        request.setHeight(10);
        request.setMinesCount(10);

        expectedResponse.setCompleted(false);

        Mockito.when(gameService.createNewGame(Mockito.any(NewGameRequest.class)))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/minesweeper/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.game_id").value("game-id"))
                .andExpect(jsonPath("$.completed").value(false));

        Mockito.verify(gameService, Mockito.times(1)).createNewGame(Mockito.any(NewGameRequest.class));
    }

    @Test
    void makeTurn_shouldReturnCorrectUpdatedGameState() throws Exception {
        GameTurnRequest request = new GameTurnRequest();
        request.setGameId("game-id");
        request.setRow(5);
        request.setCol(5);

        Game game = Mockito.mock(Game.class);

        expectedResponse.setCompleted(true);

        Mockito.when(gameService.findGameById("game-id")).thenReturn(game);
        Mockito.when(game.makeTurn(5, 5)).thenReturn(expectedResponse);

        mockMvc.perform(post("/minesweeper/turn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.game_id").value("game-id"))
                .andExpect(jsonPath("$.completed").value(true));

        Mockito.verify(gameService, Mockito.times(1)).findGameById("game-id");
        Mockito.verify(game, Mockito.times(1)).makeTurn(5, 5);
    }
}
