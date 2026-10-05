/**
 * Interface representing a move-selection strategy for the computer player.
 */
public interface Strategy {
    /**
     * Determines the computer's move based on the strategy and optional player input.
     *
     * @param playerMove the move selected by the player ("R", "P", or "S")
     * @return the move selected by the computer ("R", "P", or "S")
     */
    String getMove(String playerMove);
}
