import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman  {
    public static void main(String[] args) throws FileNotFoundException {

        // "user.dir" gives us the program's working directory
        Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/assets/usa.txt"));
        Scanner keyboard = new Scanner(System.in);
        
        // This list will contain all the words from the file (easier to work with)
        List<String> words = new ArrayList<>();

        // Loop to add all the words into our words list
        while(scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        // Randomizer to pick a random word from words (for Single Player Mode)
        Random rand = new Random();

        // words.size() to get the upper bound for rand.nextInt()
        String word = words.get(rand.nextInt(words.size()));

        System.out.println(word);

        // List of Characters the player has guessed
        List<Character> playerGuesses = new ArrayList<>();

        while(true) {
            getPlayerGuess(keyboard, word, playerGuesses);
            if(printWordState(word, playerGuesses)) break;
        }

        System.out.println("You win!");


    }

    private static void getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.print("Please enter a letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        // Check what char's the player has guessed
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++) {
            if(playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }
        System.out.println();

        return word.length() == correctCount;
    }
}
