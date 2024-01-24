package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import positions.GamePositions;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void blackToMoveOnStart() {
        GameController gameController = new GameController(new Board());
        assertTrue(gameController.isBlackToMove());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckLength(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        GameController gameController = new GameController(board);
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(1, directionsWithOppositeColor.size());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckCoordinates(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        GameController gameController = new GameController(board);
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        Direction chosenDirection = directionsWithOppositeColor.getFirst();
        assertEquals(expectedX, chosenDirection.x());
        assertEquals(expectedY, chosenDirection.y());
    }




    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideDirectionsWithOppositeColorAfterD3")
    void findDirectionsWithOppositeColorAfterD3IsPlayed(String inputCoords, ArrayList<Direction> expectedDirections) {
        Board board = GamePositions.d3IsPlayed();
        GameController gameController = new GameController(board);
        gameController.swapTurn();
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(expectedDirections, directionsWithOppositeColor);

    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesWhichDoesNotHaveOppositeColor")
    void findDirectionsWithOppositeColorImpossible(String inputPosition) {
        Board board = new Board();
        GameController gameController = new GameController(board);
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputPosition));
        assertEquals(0, directionsWithOppositeColor.size());
    }

    @Test
    void changeTurnAfterMove() {
        Board board = new Board();
        GameController gameController = new GameController(board);
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }}, gameController.getCurrentPlayerColor());
        board.applyMoveToBoard(c4);
        gameController.swapTurn();
        assertFalse(gameController.isBlackToMove());
    }

    @Test
    void PlayerAreCorrectOnStart() {
        Board board = new Board();
        GameController gameController = new GameController(board);
        assertEquals(ColoredPawn.BLACK, gameController.getCurrentPlayerColor());
        assertEquals(ColoredPawn.WHITE, gameController.getCurrentPlayerColor().opposite());
    }

    @Test
    void playersUpdatedAfterMove() {
        Board board = new Board();
        GameController gameController = new GameController(board);
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }}, gameController.getCurrentPlayerColor());
        board.applyMoveToBoard(c4);
        gameController.swapTurn();
        assertEquals(ColoredPawn.WHITE, gameController.getCurrentPlayerColor());
        assertEquals(ColoredPawn.BLACK, gameController.getCurrentPlayerColor().opposite());
    }

    @Test
    void onStartThereAreFourValidMoves() {
        Board board = new Board();
        GameController gameController = new GameController(board);
        gameController.computeValidMoves();
        int nMovesFound = gameController.numberOfValidMoves();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = new Board();
        GameController gameController = new GameController(board);
        gameController.computeValidMoves();
        ValidMove obtainedValidMove = gameController.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.BLACK, obtainedValidMove.currentColor());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesAfterD3")
    void afterD3ValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = GamePositions.d3IsPlayed();
        GameController gameController = new GameController(board);
        gameController.swapTurn();
        gameController.computeValidMoves();
        ValidMove obtainedValidMove = gameController.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.WHITE, obtainedValidMove.currentColor());
    }

    @Test
    void ifEmptyTileInsideTheDirectionMoveIsNotValid() {
        Board board = GamePositions.emptyTileBetweenTwoPawns();
        GameController gameController = new GameController(board);
        gameController.computeValidMoves();
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        BoardTile d1 = new BoardTile("d1");
        assertTrue(validMoves.stream().noneMatch(validMove -> validMove.position().equals(d1)));
    }

    @Test
    void PlayerCanDoAnyMoveAmongValidMoves() {
        Board board = new Board();
        GameController gameController = new GameController(board);
        gameController.computeValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(gameController.numberOfValidMoves());
        ValidMove randomMove = gameController.getValidMoves().get(extracted);
        Optional<ValidMove> thisShouldNotBeNull = gameController.isValid(randomMove.position());
        assertTrue(thisShouldNotBeNull.isPresent());
    }


}
