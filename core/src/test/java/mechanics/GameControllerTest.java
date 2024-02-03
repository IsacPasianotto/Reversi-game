package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import player.computer.RandomPlayer;
import positions.GamePositions;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckLength(String inputCoords, int expectedX, int expectedY) {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        ArrayList<Direction> directionsWithOppositeColor = game.getGameController().findDirectionsWithOppositeColor(new BoardTile(inputCoords), game.getCurrentPlayerColor());
        assertEquals(1, directionsWithOppositeColor.size());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckCoordinates(String inputCoords, int expectedX, int expectedY) {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        ArrayList<Direction> directionsWithOppositeColor = game.getGameController().findDirectionsWithOppositeColor(new BoardTile(inputCoords),game.getCurrentPlayerColor());
        Direction chosenDirection = directionsWithOppositeColor.getFirst();
        assertEquals(expectedX, chosenDirection.x());
        assertEquals(expectedY, chosenDirection.y());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideDirectionsWithOppositeColorAfterD3")
    void findDirectionsWithOppositeColorAfterD3IsPlayed(String inputCoords, ArrayList<Direction> expectedDirections) {
        Board board = GamePositions.d3IsPlayed();
        Game game = new Game(board, new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.swapTurn();
        ArrayList<Direction> directionsWithOppositeColor = game.getGameController().findDirectionsWithOppositeColor(new BoardTile(inputCoords),game.getCurrentPlayerColor());
        assertEquals(expectedDirections, directionsWithOppositeColor);

    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesWhichDoesNotHaveOppositeColor")
    void findDirectionsWithOppositeColorImpossible(String inputPosition) {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        ArrayList<Direction> directionsWithOppositeColor = game.getGameController().findDirectionsWithOppositeColor(new BoardTile(inputPosition), game.getCurrentPlayerColor());
        assertEquals(0, directionsWithOppositeColor.size());
    }


    @Test
    void playersUpdatedAfterMove() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.playASingleTurn();
        assertEquals(ColoredPawn.WHITE, game.getCurrentPlayerColor());
        assertEquals(ColoredPawn.BLACK, game.getCurrentPlayerColor().opposite());
    }

    @Test
    void onStartThereAreFourValidMoves() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.getGameController().computeValidMoves(game.getCurrentPlayerColor());
        int nMovesFound = game.getGameController().getValidMoves().size();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.getGameController().computeValidMoves(game.getCurrentPlayerColor());
        ValidMove obtainedValidMove = game.getGameController().getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.BLACK, obtainedValidMove.currentColor());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesAfterD3")
    void afterD3ValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = GamePositions.d3IsPlayed();
        Game game = new Game(board, new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.swapTurn();
        game.getGameController().computeValidMoves(game.getCurrentPlayerColor());
        ValidMove obtainedValidMove = game.getGameController().getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.WHITE, obtainedValidMove.currentColor());
    }

    @Test
    void ifEmptyTileInsideTheDirectionMoveIsNotValid() {
        Board board = GamePositions.emptyTileBetweenTwoPawns();
        Game game = new Game(board, new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        game.getGameController().computeValidMoves(game.getCurrentPlayerColor());
        ArrayList<ValidMove> validMoves = game.getGameController().getValidMoves();
        BoardTile d1 = new BoardTile("d1");
        assertTrue(validMoves.stream().noneMatch(validMove -> validMove.position().equals(d1)));
    }

    @Test
    void PlayerCanDoAnyMoveAmongValidMoves() {
        Game game = new Game(new Board(), new RandomPlayer(ColoredPawn.BLACK), new RandomPlayer(ColoredPawn.WHITE));
        GameController gameController = game.getGameController();
        gameController.computeValidMoves(game.getCurrentPlayerColor());
        Random rnd = new Random();
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        int extracted = rnd.nextInt(validMoves.size());
        ValidMove randomMove = validMoves.get(extracted);
        Optional<ValidMove> thisShouldNotBeNull = gameController.isValid(randomMove.position());
        assertTrue(thisShouldNotBeNull.isPresent());
    }
}