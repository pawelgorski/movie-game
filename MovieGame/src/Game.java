import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Game {
    private String movieTitle;
    private StringBuilder codedTitle;
    private StringBuilder guessedLetters;
    private StringBuilder wrongLetters;
    private int points;
    private boolean hasWon;
    private boolean endOfGame;
    private int codedLetters;

    public Game (){
        chooseMovieTitle("movies.txt");
        this.codedTitle = new StringBuilder();
        this.guessedLetters = new StringBuilder();
        this.wrongLetters = new StringBuilder();
        this.points = 10;
        this.hasWon = false;
        this.endOfGame = false;
        this.codedLetters = movieTitle.length();
    }
    public int getScore(){
        return this.points;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public boolean getHasWon(){
        return this.hasWon;
    }

    public boolean getEndOfGame(){
        return this.endOfGame;
    }

    /**
     * Function chooses movie title from file
     * @param fileName - file name, where titles are stored
     */
    public void chooseMovieTitle(String fileName){
        File file = new File(fileName);
        Scanner fileScanner;
        int counter = 0;
        ArrayList<String> listOfMovies = new ArrayList<>();

        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException exception){
            System.out.println("Cannot find file " + file + ".");
            return;
        }

        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            if (listOfMovies.contains(line))
                continue;
            else {
                listOfMovies.add(line);
                counter++;
            }
        }

        int filmNumber = 0;
        if (counter > 0) {
            filmNumber = (int)(Math.random()*counter);
        }

        this.movieTitle = listOfMovies.get(filmNumber);
    }

    /**
     * Function displays encoded title of the movie
     */
    public void displayTitle(){
        if (this.movieTitle.length() == 0)
            return;

        codedTitle.delete(0, codedTitle.length());
        int codedLettersCounter = 0;

        for(int i = 0; i < this.movieTitle.length(); i++){
            if(!(this.guessedLetters.toString().contains(String.valueOf(this.movieTitle.charAt(i)))) &&
                    Character.isLetter(this.movieTitle.charAt(i))){
                codedTitle.append('#');
                codedLettersCounter++;
            }
            else codedTitle.append(this.movieTitle.charAt(i));
        }
        System.out.println("You are guessing: " + codedTitle.toString());
        this.codedLetters = codedLettersCounter;
    }

    public void checkLetter(char letter) {

        if (this.movieTitle.toLowerCase().contains(String.valueOf(Character.toLowerCase(letter))) &&
                !(this.guessedLetters.toString().contains(String.valueOf(Character.toLowerCase(letter))))){
            this.guessedLetters.append(Character.toLowerCase(letter));
        } else if (!(this.movieTitle.toLowerCase().contains(String.valueOf(Character.toLowerCase(letter)))) &&
                !(this.wrongLetters.toString().contains(String.valueOf(Character.toLowerCase(letter))))){
            this.wrongLetters.append(Character.toLowerCase(letter));
            this.points--;
        } else
            System.out.println("You have already tried this letter.");

    }

    public void displayWrongLetters(){
        int size = this.wrongLetters.length();

        System.out.print("You have guessed (" + size + ") wrong letters: ");
        for (int i = 0; i < size; i++) {
            System.out.print(this.wrongLetters.charAt(i) + " ");
        }
        System.out.println();

    }

    public void checkWin(){
        if (this.codedLetters <=0) {
            this.hasWon = true;
            this.endOfGame = true;
        } else if(this.points <= 0) {
            this.hasWon = false;
            this.endOfGame = true;
        } else {
            this.hasWon = false;
        }
    }

}
