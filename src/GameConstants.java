/**
 * Application constants to avoid hardcoding strings, dimensions, and limits.
 */
public interface GameConstants {
    // Game Move Codes
    String MOVE_ROCK = "R";
    String MOVE_PAPER = "P";
    String MOVE_SCISSORS = "S";
    String MOVE_UNKNOWN = "X";

    // Move Labels
    String LABEL_ROCK = "Rock";
    String LABEL_PAPER = "Paper";
    String LABEL_SCISSORS = "Scissors";

    // Strategy Names
    String STRATEGY_CHEAT = "Cheat";
    String STRATEGY_LEAST_USED = "Least Used";
    String STRATEGY_MOST_USED = "Most Used";
    String STRATEGY_LAST_USED = "Last Used";
    String STRATEGY_RANDOM = "Random";

    // UI Strings & Titles
    String FRAME_TITLE = "Rock Paper Scissors Game";
    String BTN_QUIT = "Quit";
    String LABEL_PLAYER_WINS = "Player Wins:";
    String LABEL_COMPUTER_WINS = "Computer Wins:";
    String LABEL_TIES = "Ties:";
    String BORDER_CONTROL_PANEL = "Make Your Choice";
    String BORDER_STATS_PANEL = "Game Statistics";
    String BORDER_RESULTS_PANEL = "Game Log";

    // Image File Paths (placed in src directory)
    String ICON_ROCK_PATH = "rock.png";
    String ICON_PAPER_PATH = "paper.png";
    String ICON_SCISSORS_PATH = "scissors.png";
    String ICON_QUIT_PATH = "quit.png";

    // Probability Thresholds (Inclusive upper bounds, out of 100)
    int PROBABILITY_CHEAT_MAX = 10;
    int PROBABILITY_LEAST_USED_MAX = 30;
    int PROBABILITY_MOST_USED_MAX = 50;
    int PROBABILITY_LAST_USED_MAX = 70;
    int PROBABILITY_RANGE_MAX = 100;

    // GUI Dimensions
    int FRAME_WIDTH = 650;
    int FRAME_HEIGHT = 500;
    int TEXT_FIELD_COLUMNS = 6;
    int LOG_ROWS = 12;
    int LOG_COLS = 50;
}