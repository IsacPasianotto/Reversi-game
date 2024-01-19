package mechanics;

import board.Board;
import board.ValidMove;
import board.coords.BoardTile;
import board.coords.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import positions.GamePositions;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidMovesCheckerTest {

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#providesDirectionsWithOppositeColorOnStart")
    void findDirectionsWithOppositeColor(String inputCoords, int expectedX, int expectedY) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> possiblyValidDirections = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(1, possiblyValidDirections.size());
        Direction chosenDirection = possiblyValidDirections.get(0);
        assertEquals(expectedX, chosenDirection.getX());
        assertEquals(expectedY, chosenDirection.getY());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideDirectionsWithOppositeColorAfterD3")
    void findDirectionsWithOppositeColorAfterD3IsPlayed(String inputCoords, ArrayList<Direction> expectedDirections) {
        Board board = GamePositions.d3IsPlayed();
        board.swapTurn();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> possiblyValidDirections = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputCoords));
        assertEquals(expectedDirections, possiblyValidDirections);

    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesWhichDoesNotHaveOppositeColor")
    void findDirectionsWithOppositeColorImpossible(String inputPosition) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        ArrayList<Direction> possiblyValidDirections = validMovesChecker.findDirectionsWithOppositeColor(new BoardTile(inputPosition));
        assertEquals(0, possiblyValidDirections.size());
    }


    @Test
    void onStartThereAreFourValidMoves() {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        int nMovesFound = validMovesChecker.getValidMoves().size();
        assertEquals(4, nMovesFound);
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesOnStart")
    void onStartValidMovesAreCorrect(String truePositionCenter, int discoveredOrder,  ArrayList<Direction> trueDirections) {
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove obtainedValidMove = validMovesChecker.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.getPosition().toString());
        assertEquals(trueDirections, obtainedValidMove.getValidDirections());
    }

    @ParameterizedTest
    @MethodSource("positions.ValidMovesPositions#provideCoordinatesForValidMovesAfterD3")
    void afterD3ValidMovesAreCorrect(String truePositionCenter, int discoveredOrder, ArrayList<Direction> trueDirections) {
        Board board = GamePositions.d3IsPlayed();
        board.swapTurn();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove obtainedValidMove = validMovesChecker.getValidMoves().get(discoveredOrder);
        assertEquals(truePositionCenter, obtainedValidMove.getPosition().toString());
        assertEquals(trueDirections, obtainedValidMove.getValidDirections());
    }

    @Test
    void ifEmptyTileInsideTheDirectionMoveIsNotValid() {
        Board board = GamePositions.emptyTileBetweenTwoPawns();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ArrayList<ValidMove> validMoves = validMovesChecker.getValidMoves();
        assertTrue(validMoves.stream().noneMatch(validMove -> validMove.getPosition().equals(new BoardTile("d1"))));
    }

    @Test
    void PlayerCanDoAnyMoveAmongValidMoves() {
        // test made due to a discovered bug for which the player was allowed to play only the first valid move of the ValidMovesChecker
        Board board = new Board();
        ValidMovesChecker validMovesChecker = new ValidMovesChecker(board);
        validMovesChecker.computeValidMoves();
        ValidMove notFirstValidMove = validMovesChecker.getValidMoves().get(1);
        Optional<ValidMove> thisShouldNotBeNull = validMovesChecker.IsValid(notFirstValidMove.getPosition());
        assertTrue(thisShouldNotBeNull.isPresent());

    }


}
