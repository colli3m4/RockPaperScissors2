/**
 * Computer strategy where the computer cheats by looking at the player's move
 * and picking the exact move that beats it.
 */
public class Cheat implements Strategy {
    @Override
    public String getMove(String playerMove) {
        if (playerMove == null) {
            return GameConstants.MOVE_UNKNOWN;
        }

        switch (playerMove) {
            case GameConstants.MOVE_ROCK:
                return GameConstants.MOVE_PAPER;
            case GameConstants.MOVE_PAPER:
                return GameConstants.MOVE_SCISSORS;
            case GameConstants.MOVE_SCISSORS:
                return GameConstants.MOVE_ROCK;
            default:
                return GameConstants.MOVE_UNKNOWN;
        }
    }
}