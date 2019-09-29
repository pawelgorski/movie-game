import java.util.Scanner;

public class GameUI {

    public static void printScore(Game game){
        int score = game.getScore();
        if (score > 1){
            System.out.println("You have " + score + " lifes left.");
        }
        else {
            System.out.println("You have " + score + " life left.");
        }
        System.out.println();
    }

    public static char guessLetter(){
        System.out.println();
        System.out.print("Guess a letter: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while (input !="exit" && !(Character.isLetter(input.charAt(0)))){
            input = scanner.next();
        }
        if (input.equals("exit")){
            return '\u001B';
        } else {
            return input.charAt(0);
        }

    }

    public static void gameOver(Game game){
        String movieTitle = game.getMovieTitle();

        System.out.println();

        if (game.getHasWon()) {
            System.out.println("Congratulations, you have guessed all the letters!" );
        } else {
            System.out.println("Game Over! You lose." );
        }

        System.out.println("Movie title was: " + movieTitle + ".");
    }


    public static void main(String [] args){
        Game game = new Game();
        System.out.println("Welcome to the Movie Game.");
        System.out.println("Your goal is to guess all the letters of a randomly chosen movie title.");
        System.out.println("If you want to leave a game, type \"exit\".");

        char letter = '0';
        while (!(game.getEndOfGame()) && (letter != '\u001B')){
            System.out.println();
            printScore(game);
            game.displayTitle();
            game.displayWrongLetters();
            game.checkWin();
            if(!game.getEndOfGame()){
                letter = guessLetter();
                if (letter != '\u001B')
                    game.checkLetter(letter);
            }

        }

        if (letter!= '\u001B') {
            gameOver(game);
        }

        System.out.println();
        System.out.println("Thank you for playing Movie Game!");


    }


}
