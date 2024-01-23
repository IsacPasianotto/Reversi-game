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

class ValidMovesCheckerTest {

    @Test
    void blackToMoveOnStart() {
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(new Board());
        assertTrue(validMovesChecker.isBlackToMove());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckLength(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> directionsWithOppositeColor = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(1, directionsWithOppositeColor.size());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColorCheckCoordinates(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> directionsWithOppositeColor = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        Direction chosenDirection = directionsWithOppositeColor.getFirst();
        assertEquals(expectedX, chosenDirection.x());
        assertEquals(expectedY, chosenDirection.y());
    }




    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideDirectionsWithOppositeColorAfterD3")
    void findDirectionsWithOppositeColorAfterD3IsPlayed(String inputCoords, ArrayList<Direction> expectedDirections) {
        Board board = GamePositions.d3IsPlayed();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.swapTurn();
        ArrayList<Direction> directionsWithOppositeColor = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(expectedDirections, directionsWithOppositeColor);

    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesWhichDoesNotHaveOppositeColor")
    void findDirectionsWithOppositeColorImpossible(String inputPosition) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> directionsWithOppositeColor = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputPosition));
        assertEquals(0, directionsWithOppositeColor.size());
    }

    @Test
    void changeTurnAfterMove() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }}, validMovesChecker.getCurrentPlayerColor());
        board.applyMoveToBoard(c4);
        validMovesChecker.swapTurn();
        assertFalse(validMovesChecker.isBlackToMove());
    }

    @Test
    void PlayerAreCorrectOnStart() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        assertEquals(ColoredPawn.BLACK, validMovesChecker.getCurrentPlayerColor());
        assertEquals(ColoredPawn.WHITE, validMovesChecker.getOppositePlayerColor());
    }

    @Test
    void playersUpdatedAfterMove() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ValidMove c4 = new ValidMove(new BoardTile("c4"), new ArrayList<>() {{
            add(new Direction(0, 1));
        }}, validMovesChecker.getCurrentPlayerColor());
        board.applyMoveToBoard(c4);
        validMovesChecker.swapTurn();
        assertEquals(ColoredPawn.WHITE, validMovesChecker.getCurrentPlayerColor());
        assertEquals(ColoredPawn.BLACK, validMovesChecker.getOppositePlayerColor());
    }

    @Test
    void onStartThereAreFourValidMoves() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        int nMovesFound = validMovesChecker.numberOfValidMoves();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove obtainedValidMove = validMovesChecker.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.BLACK, obtainedValidMove.currentColor());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesAfterD3")
    void afterD3ValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = GamePositions.d3IsPlayed();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.swapTurn();
        validMovesChecker.computeValidMoves();
        ValidMove obtainedValidMove = validMovesChecker.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.position().toString());
        assertEquals(trueDirections, obtainedValidMove.validDirections());
        assertEquals(ColoredPawn.WHITE, obtainedValidMove.currentColor());
    }

    @Test
    void ifEmptyTileInsideTheDirectionMoveIsNotValid() {
        Board board = GamePositions.emptyTileBetweenTwoPawns();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ArrayList<ValidMove> validMoves = validMovesChecker.getValidMoves();
        assertTrue(validMoves.stream().noneMatch(validMove -> validMove.position().equals(new BoardTile("d1"))));
    }

    @Test
    void PlayerCanDoAnyMoveAmongValidMoves() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        Random rnd = new Random();
        int extracted = rnd.nextInt(validMovesChecker.numberOfValidMoves());
        ValidMove randomMove = validMovesChecker.getValidMoves().get(extracted);
        Optional<ValidMove> thisShouldNotBeNull = validMovesChecker.IsValid(randomMove.position());
        assertTrue(thisShouldNotBeNull.isPresent());
    }


}
