import java.util.Random;
import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public void play() {
        //start game
        printGameRules();
        String choice = input.nextLine().toUpperCase(); //change to lowercase for consistency

        //initialize variables
        ScoreBoard scoreBoard = new ScoreBoard();

        while (!choice.equals("quit")) //do the following if the user does not put in "quit"
        {
            Options choicenum = getChoices(choice);
            while(choicenum == null) //continue while user input is still not valid
            {
                System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
                choice = input.nextLine().toUpperCase();
                choicenum = getChoices(choice);
            }
            Options compnum = getChoiceComputer();

            getScores(choicenum, compnum, scoreBoard);
            printResults(scoreBoard);
            choice = input.nextLine(); //prompt for new user input
            choice = choice.toLowerCase();
        }
    }

    private static void printResults(ScoreBoard scoreBoard) {
        System.out.println("wins:" + scoreBoard.getWins() + "\nloses:" + scoreBoard.getLosses() + "\nties:" + scoreBoard.getTie()); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n"); //start game again
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }

    private static void getScores(Options choicenum, Options compnum, ScoreBoard scoreBoard) {
        if(choicenum == compnum) //tie cases
        {
            tie(scoreBoard);
        }
        else if (choicenum == Options.OPTION_ROCK && compnum == Options.OPTION_SCISSORS)
        {
            wins(scoreBoard);
        }
        else if (choicenum == Options.OPTION_SCISSORS && compnum == Options.OPTION_PAPER)
        {
            wins(scoreBoard);
        }
        else if (choicenum == Options.OPTION_PAPER && compnum == Options.OPTION_ROCK)
        {
            wins(scoreBoard);
        }
        lose(scoreBoard);
    }

    private static void tie(ScoreBoard scoreBoard) {
        System.out.println("It's a tie");
        scoreBoard.incrementTie();
    }

    private static void lose(ScoreBoard scoreBoard) {
        System.out.println("you lose.");
        scoreBoard.incrementLosses();
    }

    private static void wins(ScoreBoard scoreBoard) {
        System.out.println("you win!");
        scoreBoard.incrementWins();
    }

    private Options getChoices(String choice){
        Options selectedOption = null;

        if (choice.equals("quit"))
            System.exit(0);
        try {
            selectedOption = Options.valueOf(choice);
        } catch(Exception e) {
            return null;
        }
        return selectedOption;
    }

    private Options getChoiceComputer() {
        Options options = Options.values()[random.nextInt(3)];
        System.out.println("Computer chose " + options.toString().toLowerCase());
        return options;
    }

    private static void printGameRules() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }
}
