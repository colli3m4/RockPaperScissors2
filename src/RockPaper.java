import java.util.Scanner;

public class RockPaper {
    public static <string> void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean Play = false;
        System.out.println("Welcome to Rock Paper Scissors!");
        while (Play) {
            string PlayerA = (string) "";
            string PlayerB = (string) "";
        }
        //get player A move

        //Get a valid move from Player A
        do {
            System.out.println("Enter move for player R, P, or S");
            String PlayerA = input.nextLine();
            System.out.println("Player A:  " + PlayerA);


            //check for three conditions R,P or S
            if (!PlayerA.equalsIgnoreCase("R")) {
                if (!PlayerA.equalsIgnoreCase("P")) {
                    if (!PlayerA.equalsIgnoreCase("S")) {
                        System.out.println("Invalid input! Please enter only R, P, or S.");
                    }
                }
            }

            while

            (!PlayerA.equalsIgnoreCase("R") &&
                    !PlayerA.equalsIgnoreCase("P") &&
                    !PlayerA.equalsIgnoreCase("S")) ;


            //Get a valid move from Player B
            boolean PlayAgain = false;

            do {
                System.out.println("Enter move for player R, P, or S");
                String PlayerB = input.nextLine();
                System.out.println("Player B:  " + PlayerB);

                //check for three conditions R,P or S
                if (!PlayerB.equalsIgnoreCase("R")) {
                    if (!PlayerB.equalsIgnoreCase("P")) {

                    }
                    boolean S;

                    if (PlayerB.equalsIgnoreCase("S")) {
                    }
                    System.out.println("Invalid input! Please enter only R, P, or S.");

                    boolean b;
                    b = !(PlayerB.equalsIgnoreCase("R") ||
                            !(!PlayerB.equalsIgnoreCase("P") &&
                                    !PlayerB.equalsIgnoreCase("S")));
                }

                if (PlayerA.equals("R") && PlayerB.equals("R"))
                    System.out.println("R x R Its a Tie");

                else if (PlayerA.equals("R") && PlayerB.equals("S"))
                    System.out.println("R X S Rock Breaks Scissors PlayerB wins ");


                if (PlayerA.equals("P") && "PlayerB".equals("R"))
                    System.out.println("P X R Paper Covers Rock PLayer A wins");
                else //PlayerB is Scissors
                {
                    if (PlayerA.equals("S") && "PlayerB".equals("P"))
                        System.out.println("Scissors Cuts Paper Player A wins");

                }
                System.out.println("Player again? yes/no: ");
            }
            while (Play);
            System.out.println("Player again? yes/no: ");
            System.out.println("Player yes/no: ");
        } while (true);
    }
}
