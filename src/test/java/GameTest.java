import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    public static final int ROCK = 0;
    public static final int PAPER = 1;
    public static final int SCISSORS = 2;
    @InjectMocks
    private Game game;

    @Mock
    Scanner scanner;

    @Mock
    Random random;

    private ByteArrayOutputStream out;

    @Before
    public void setup(){
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void game_when_quitTest(){
        when(scanner.nextLine()).thenReturn("Quit");
        game.play();
    }

    @Test
    public void game_rockTest() {
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        game.play();
    }

    @Test
    public void game_searching_a_wordTest() {
        when(scanner.nextLine()).thenReturn("Quit");
        game.play();
        Assert.assertTrue(out.toString().contains("Let's play Rock"));
    }

    @Test
    public void game_rock_beat_scissorsTest() {
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(SCISSORS);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose scissors"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void game_scissors_beat_paperTest() {
        when(scanner.nextLine()).thenReturn("Scissors").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(PAPER);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void game_paper_beat_rockTest() {
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(ROCK);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose rock"));
        Assert.assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void game_paper_then_tieTest() {
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(PAPER);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose paper"));
        Assert.assertTrue(out.toString().contains("ties:1"));
    }

    @Test
    public void game_computer_winsTest() {
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(SCISSORS);
        game.play();
        Assert.assertTrue(out.toString().contains("Computer chose scissors"));
        Assert.assertTrue(out.toString().contains("loses:1"));
    }


}
