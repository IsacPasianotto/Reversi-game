package mechanics;

public class GameTest {

    // TODO --> implement test for the game class


//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//
//    @BeforeEach
//    public void setUp() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//   }
//    @Test
//    void noStrangeInputAllowed() {
//        Board board = new Board();
//        UserInputReader mockedInputReader = mock(UserInputReader.class);
//        String simulatedInput = "This is clearly not a valid move";
//        try {
//            when(mockedInputReader.readInput()).thenReturn(simulatedInput);
//        }  catch (QuitGameException | UndoException notThisCase) { }
//        ValidMove validMove = null;
//        Exception e = assertThrows(NumberFormatException.class, () -> {
//            ValidMove ValidMove = mockedInputReader.getMove();
//        });
//
//        // assertTrue(outputStreamCaptor.toString().trim().contains(" "));
//    }
////
//    @Test
//    void bothPlayersCanNotMove() {
//
//        Board board = impossibleToMovePosition();
//        UserInputReader mockedInputReader = mock(UserInputReader.class);
//        String simulatedInput = "This does not matter, the game should end before asking for input";
//        when(mockedInputReader.readInput()).thenReturn(simulatedInput);
//        mechanics.Game game = new mechanics.Game(board, mockedInputReader);
//        game.play();
//        assertTrue(outputStreamCaptor.toString().trim().contains("No valid moves for both players. mechanics.Game over."));
//        assertTrue(board.isGameOver());
//
//   }
//    @AfterEach
//    public void tearDown() {
//        System.setOut(standardOut);
//        System.out.println("---------------------------");
//        System.out.println(outputStreamCaptor.toString());
//        System.out.println("----------------------------");
//    }


}
