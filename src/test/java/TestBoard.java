
import com.assessment.board.logic.GamePlay;
import org.junit.Test;


public class TestBoard {
    @Test
    public void testBoard() {
       // GamePlay mocked = mock(GamePlay.class);
       // when(mocked.getBoard()).thenThrow(new NullPointerException());

        GamePlay gamePlay = new GamePlay("Player 1","Player 2");
        gamePlay.play(1,1);

    }
}
