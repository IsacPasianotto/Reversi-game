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

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckLength(String inputCoords, int expectedX, int expectedY) {
        GameController gameController = new GameController(new Board());
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords), ColoredPawn.BLACK);
        assertEquals(1, directionsWithOppositeColor.size());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckCoordinates(String inputCoords, int expectedX, int expectedY) {
        GameController gameController = new GameController(new Board());
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords),ColoredPawn.BLACK);
        Direction chosenDirection = directionsWithOppositeColor.get(0);
        assertEquals(expectedX, chosenDirection.x());
        assertEquals(expectedY, chosenDirection.y());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideDirectionsWithOppositeColorAfterD3")
    void findDirectionsWithOppositeColorAfterD3IsPlayed(String inputCoords, ArrayList<Direction> expectedDirections) {
        Board board = GamePositions.d3IsPlayed();
        GameController gameController = new GameController(board);
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputCoords),ColoredPawn.WHITE);
        assertEquals(expectedDirections, directionsWithOppositeColor);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesWhichDoesNotHaveOppositeColor")
    void findDirectionsWithOppositeColorImpossible(String inputPosition) {
        GameController gameController = new GameController(new Board());
        ArrayList<Direction> directionsWithOppositeColor = gameController.findDirectionsWithOppositeColor(new BoardTile(inputPosition), ColoredPawn.BLACK);
        assertEquals(0, directionsWithOppositeColor.size());
    }

    @Test
    void onStartThereAreFourValidMoves() {
        GameController gameController = new GameController(new Board());
        gameController.computeValidMoves(ColoredPawn.BLACK);
        int nMovesFound = gameController.getValidMoves().size();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        GameController gameController = new GameController(new Board());
        gameController.computeValidMoves(ColoredPawn.BLACK);
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
        gameController.computeValidMoves(ColoredPawn.WHITE);
        ValidMove obtainedValidMove = gameController.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.WHITE, obtainedValidMove.currentColor());
    }

    @Test
    void ifEmptyTileInsideTheDirectionMoveIsNotValid() {
        Board board = GamePositions.emptyTileBetweenTwoPawns();
        GameController gameController = new GameController(board);
        gameController.computeValidMoves(ColoredPawn.BLACK);
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        BoardTile d1 = new BoardTile("d1");
        assertTrue(validMoves.stream().
                noneMatch(validMove -> validMove.position().
                        equals(d1)));
    }

    @Test
    void PlayerCanDoAnyMoveAmongValidMoves() {
        GameController gameController = new GameController(new Board());
        gameController.computeValidMoves(ColoredPawn.BLACK);
        Random rnd = new Random();
        ArrayList<ValidMove> validMoves = gameController.getValidMoves();
        int extracted = rnd.nextInt(validMoves.size());
        ValidMove randomMove = validMoves.get(extracted);
        Optional<ValidMove> thisShouldNotBeNull = gameController.isValid(randomMove.position());
        assertTrue(thisShouldNotBeNull.isPresent());
    }
}