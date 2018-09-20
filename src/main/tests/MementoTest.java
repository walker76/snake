import exceptions.SnakeBuilderError;
import game.Game;
import game.GameMaker;
import game.GameRecord;
import org.junit.jupiter.api.Test;
import resources.Cell;
import resources.SnakeBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link MementoTest} tests the Memento design pattern.
 *
 * @author Ian laird
 */
public class MementoTest {
    private GameRecord gr, gr2;
    private Game g;

    /**
     * This stores a Game as a memento and then retrieves it.
     *
     * @throws SnakeBuilderError -this will not be thrown
     * @author Ian laird
     */
    @Test
    void testRestoration() throws SnakeBuilderError {
        Cell player1Cell = Cell.createRandom(100, 100);
        Cell player2Cell = Cell.createRandom(100, 100);
        Cell puCell = Cell.createRandom(100, 100);
        g = GameMaker.generateGame(true);
        g.setPlayerOne(new SnakeBuilder().init().setColor(Color.BLACK).setStart(player1Cell).collectSnakeBuilder());
        g.setPlayerTwo(new SnakeBuilder().init().setColor(Color.GREEN).setStart(player2Cell).collectSnakeBuilder());
        g.setPowerUp(puCell);
        gr = g.createRecord();
        //Now see if everything is equal
        assertEquals(gr.getSnakeOneRecord(), g.getPlayerOne());
        assertEquals(gr.getSnakeTwoRecord(), g.getPlayerTwo());
        assertEquals(gr.getPowerUpLocation(), g.getPowerUp());
        g.restoreFromOldState(gr);
        gr2 = g.createRecord();
        assertEquals(gr, gr2);
    }
}
