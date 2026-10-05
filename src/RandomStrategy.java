import java.util.Random;

/**
 * Computer strategy that selects a move completely at random.
 */
public class RandomStrategy implements Strategy {
    private final Random random;
    private final String[] moves;

    /**
     * Constructs a new RandomStrategy instance.
     */
    public RandomStrategy() {
        this.random = new Random();
        this.moves = new String[]{
                GameConstants.MOVE_ROCK,
                GameConstants.MOVE_PAPER,
                GameConstants.MOVE_SCISSORS
        };
    }

    @Override
    public String getMove(String playerMove) {
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}