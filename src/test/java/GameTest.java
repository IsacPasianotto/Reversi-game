public class GameTest {
//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//    }
//    @Test
//    void noStrangeInputAllowed() {
//
//        Board board = new Board();
//        UserInputReader mockedInputReader = mock(UserInputReader.class);
//        String simulatedInput = "ThisIsClearlyNotAValidMove";
//        when(mockedInputReader.readInput()).thenReturn(simulatedInput);
//        ValidMovesChecker movesChecker = new ValidMovesChecker(board);
//        movesChecker.computeValidMoves();
//        try {
//            mockedInputReader.askForAMove(movesChecker);
//        } catch (UndoException u) {
//        }
//        assertTrue(outputStreamCaptor.toString().trim().contains("Enter your move:"));
//    }
//
//    @Test
//    void bothPlayersCanNotMove() {
//
//        Board board = GamePositions.impossibleToMovePosition();
//        UserInputReader mockedInputReader = mock(UserInputReader.class);
//        String simulatedInput = "This does not matter, the game should end before asking for input";
//        when(mockedInputReader.readInput()).thenReturn(simulatedInput);
//        Game game = new Game(board, mockedInputReader);
//        game.play();
//        assertTrue(outputStreamCaptor.toString().trim().contains("No valid moves for both players. Game over."));
//        assertTrue(board.isGameOver());
//
//    }
//    @AfterEach
//    public void tearDown() {
//        System.setOut(standardOut);
//    }


}
