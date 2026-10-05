import javax.swing.SwingUtilities;

/**
 * Runner class to initialize and display the Rock Paper Scissors GUI.
 */
public class RockPaperScissorsRunner {
    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}