public class GameTest {

//    TODO: See how this thing can be tested
//    @Test
//    void noStrangeInputAllowed() {
//        Exception e = assertThrows(IllegalArgumentException.class, () -> {
//            Board board = new Board();
//            Player player = new Player();
//            InputReader inputReader = new InputReader();
//            InputReader mockedInputReader = mock(InputReader.class);
//            String simulatedInput = "ThisIsClearlyNotAValidMove";
//            when(mockedInputReader.readInput()).thenReturn(String.valueOf(new BufferedReader(new java.io.StringReader(simulatedInput))));
//            ValidMovesChecker movesChecker = new ValidMovesChecker(board);
//            movesChecker.computeValidMoves();
//            ValidMove validMove = new  Game().askForAMove(board, player, mockedInputReader, movesChecker);
//        });
//        assertEquals(e.getClass(), IllegalArgumentException.class);
//    }

}
