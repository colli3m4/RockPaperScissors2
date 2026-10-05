import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Main GUI frame for the Rock Paper Scissors application.
 * Manages game state, user interactions, stats, and inner strategy implementations.
 */
public class RockPaperScissorsFrame extends JFrame implements GameConstants {

    // Stats variables
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    // Move counter for Inner Class Strategies
    private int rockCount = 0;
    private int paperCount = 0;
    private int scissorsCount = 0;
    private String lastPlayerMove = null;

    // UI Components
    private JTextField txtPlayerWins;
    private JTextField txtComputerWins;
    private JTextField txtTies;
    private JTextArea txtResultsLog;

    // Strategies
    private final Strategy cheatStrategy;
    private final Strategy randomStrategy;
    private final Strategy leastUsedStrategy;
    private final Strategy mostUsedStrategy;
    private final Strategy lastUsedStrategy;

    private final Random probabilityGenerator;

    /**
     * Constructs the frame and initializes GUI elements and strategies.
     */
    public RockPaperScissorsFrame() {
        setTitle(FRAME_TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize External Strategies
        this.cheatStrategy = new Cheat();
        this.randomStrategy = new RandomStrategy();

        // Initialize Inner Class Strategies
        this.leastUsedStrategy = new LeastUsed();
        this.mostUsedStrategy = new MostUsed();
        this.lastUsedStrategy = new LastUsed();

        this.probabilityGenerator = new Random();

        // Build UI Sections
        createControlPanel();
        createStatsPanel();
        createLogPanel();
    }

    /**
     * Helper to return the move that beats a given move.
     */
    private String getWinningMoveAgainst(String targetMove) {
        switch (targetMove) {
            case MOVE_ROCK:
                return MOVE_PAPER;
            case MOVE_PAPER:
                return MOVE_SCISSORS;
            case MOVE_SCISSORS:
                return MOVE_ROCK;
            default:
                return MOVE_ROCK;
        }
    }

    // =========================================================================
    // Inner Class Strategies (Access internal game/player counters)
    // =========================================================================

    /**
     * Inner strategy: Assumes player uses least-frequent move and counters it.
     */
    private class LeastUsed implements Strategy {
        @Override
        public String getMove(String playerMove) {
            String leastUsedMove = MOVE_ROCK;
            int minCount = rockCount;

            if (paperCount < minCount) {
                minCount = paperCount;
                leastUsedMove = MOVE_PAPER;
            }
            if (scissorsCount < minCount) {
                leastUsedMove = MOVE_SCISSORS;
            }

            return getWinningMoveAgainst(leastUsedMove);
        }
    }

    /**
     * Inner strategy: Assumes player repeats their most-frequent move and counters it.
     */
    private class MostUsed implements Strategy {
        @Override
        public String getMove(String playerMove) {
            String mostUsedMove = MOVE_ROCK;
            int maxCount = rockCount;

            if (paperCount > maxCount) {
                maxCount = paperCount;
                mostUsedMove = MOVE_PAPER;
            }
            if (scissorsCount > maxCount) {
                mostUsedMove = MOVE_SCISSORS;
            }

            return getWinningMoveAgainst(mostUsedMove);
        }
    }

    /**
     * Inner strategy: Counters the player's last played move.
     * Falls back to Random strategy on the first turn.
     */
    private class LastUsed implements Strategy {
        @Override
        public String getMove(String playerMove) {
            if (lastPlayerMove == null) {
                return randomStrategy.getMove(playerMove);
            }
            return getWinningMoveAgainst(lastPlayerMove);
        }
    }

    // =========================================================================
    // UI Construction
    // =========================================================================

    private void createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder(BORDER_CONTROL_PANEL));

        JButton btnRock = createGameButton(LABEL_ROCK, MOVE_ROCK, ICON_ROCK_PATH);
        JButton btnPaper = createGameButton(LABEL_PAPER, MOVE_PAPER, ICON_PAPER_PATH);
        JButton btnScissors = createGameButton(LABEL_SCISSORS, MOVE_SCISSORS, ICON_SCISSORS_PATH);

        JButton btnQuit = new JButton(BTN_QUIT, loadScaledIcon(ICON_QUIT_PATH));
        btnQuit.addActionListener(e -> System.exit(0));

        controlPanel.add(btnRock);
        controlPanel.add(btnPaper);
        controlPanel.add(btnScissors);
        controlPanel.add(btnQuit);

        add(controlPanel, BorderLayout.NORTH);
    }

    private JButton createGameButton(String title, String moveCode, String iconPath) {
        JButton button = new JButton(title, loadScaledIcon(iconPath));
        button.setActionCommand(moveCode);
        button.addActionListener(new MoveButtonListener());
        return button;
    }

    private void createStatsPanel() {
        JPanel statsPanel = new JPanel(new FlowLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder(BORDER_STATS_PANEL));

        txtPlayerWins = createReadOnlyTextField();
        txtComputerWins = createReadOnlyTextField();
        txtTies = createReadOnlyTextField();

        statsPanel.add(new JLabel(LABEL_PLAYER_WINS));
        statsPanel.add(txtPlayerWins);

        statsPanel.add(new JLabel(LABEL_COMPUTER_WINS));
        statsPanel.add(txtComputerWins);

        statsPanel.add(new JLabel(LABEL_TIES));
        statsPanel.add(txtTies);

        updateStatsDisplay();
        add(statsPanel, BorderLayout.CENTER);
    }

    private JTextField createReadOnlyTextField() {
        JTextField textField = new JTextField(TEXT_FIELD_COLUMNS);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }

    private void createLogPanel() {
        JPanel logPanel = new JPanel();
        logPanel.setBorder(BorderFactory.createTitledBorder(BORDER_RESULTS_PANEL));

        txtResultsLog = new JTextArea(LOG_ROWS, LOG_COLS);
        txtResultsLog.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResultsLog);
        logPanel.add(scrollPane);

        add(logPanel, BorderLayout.SOUTH);
    }

    private ImageIcon loadScaledIcon(String path) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
            Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null; // Gracefully handles missing icons without breaking layout
        }
    }

    // =========================================================================
    // Game Logic Handling
    // =========================================================================

    private class MoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String playerMove = e.getActionCommand();

            // Track user choices
            trackPlayerMove(playerMove);

            // Determine strategy via probability bounds
            int roll = probabilityGenerator.nextInt(PROBABILITY_RANGE_MAX) + 1;
            Strategy selectedStrategy;
            String strategyName;

            if (roll <= PROBABILITY_CHEAT_MAX) {
                selectedStrategy = cheatStrategy;
                strategyName = STRATEGY_CHEAT;
            } else if (roll <= PROBABILITY_LEAST_USED_MAX) {
                selectedStrategy = leastUsedStrategy;
                strategyName = STRATEGY_LEAST_USED;
            } else if (roll <= PROBABILITY_MOST_USED_MAX) {
                selectedStrategy = mostUsedStrategy;
                strategyName = STRATEGY_MOST_USED;
            } else if (roll <= PROBABILITY_LAST_USED_MAX) {
                selectedStrategy = lastUsedStrategy;
                strategyName = STRATEGY_LAST_USED;
            } else {
                selectedStrategy = randomStrategy;
                strategyName = STRATEGY_RANDOM;
            }

            String computerMove = selectedStrategy.getMove(playerMove);
            processRound(playerMove, computerMove, strategyName);

            // Save player move for the Last Used strategy on subsequent turns
            lastPlayerMove = playerMove;
        }
    }

    private void trackPlayerMove(String move) {
        switch (move) {
            case MOVE_ROCK:
                rockCount++;
                break;
            case MOVE_PAPER:
                paperCount++;
                break;
            case MOVE_SCISSORS:
                scissorsCount++;
                break;
        }
    }

    private void processRound(String playerMove, String computerMove, String strategyName) {
        String outcomeDescription;

        if (playerMove.equals(computerMove)) {
            ties++;
            outcomeDescription = getMoveName(playerMove) + " matches " + getMoveName(computerMove) + " (Tie game!)";
        } else if (
                (playerMove.equals(MOVE_ROCK) && computerMove.equals(MOVE_SCISSORS)) ||
                        (playerMove.equals(MOVE_PAPER) && computerMove.equals(MOVE_ROCK)) ||
                        (playerMove.equals(MOVE_SCISSORS) && computerMove.equals(MOVE_PAPER))
        ) {
            playerWins++;
            outcomeDescription = getInteractionText(playerMove, computerMove) + " (Player wins!)";
        } else {
            computerWins++;
            outcomeDescription = getInteractionText(computerMove, playerMove) + " (Computer wins!)";
        }

        String logEntry = String.format("%s [Computer: %s]", outcomeDescription, strategyName);
        txtResultsLog.append(logEntry + "\n");

        updateStatsDisplay();
    }

    private String getInteractionText(String winner, String loser) {
        if (winner.equals(MOVE_ROCK) && loser.equals(MOVE_SCISSORS)) {
            return "Rock breaks scissors";
        }
        if (winner.equals(MOVE_PAPER) && loser.equals(MOVE_ROCK)) {
            return "Paper covers rock";
        }
        if (winner.equals(MOVE_SCISSORS) && loser.equals(MOVE_PAPER)) {
            return "Scissors covers paper";
        }
        return "";
    }

    private String getMoveName(String move) {
        switch (move) {
            case MOVE_ROCK:
                return LABEL_ROCK;
            case MOVE_PAPER:
                return LABEL_PAPER;
            case MOVE_SCISSORS:
                return LABEL_SCISSORS;
            default:
                return "";
        }
    }

    private void updateStatsDisplay() {
        txtPlayerWins.setText(String.valueOf(playerWins));
        txtComputerWins.setText(String.valueOf(computerWins));
        txtTies.setText(String.valueOf(ties));
    }
}