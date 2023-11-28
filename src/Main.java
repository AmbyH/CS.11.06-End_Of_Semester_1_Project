import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] words = {"HELLO", "GOODBYE", "PYTHAGORAS", "CHAIR", "BICUBIC", "MONITOR", "RECOMMENDATION", "MYOPIC", "VISION", "PROJECT"};
        boolean playA = false;
        boolean playAgain = true;
        while (playAgain) {
            String word = getRandomWord(words);
            System.out.println(word);
            int guesses = 8;
            System.out.println("Hello, welcome to Hangman!!! :)");
            System.out.println("Input a letter to get started!");
            boolean beat = false;
            String statement = "";
            for (int i = 0; i < word.length(); i++) { //setting statement as word but in -s
                statement = statement + "-";
            }
            while (!beat) {
                String newstatement = "";
                Scanner scanner = new Scanner(System.in);
                System.out.println("Your word looks like this: ");
                System.out.print(statement);
                String input = (scanner.nextLine()).toUpperCase();
                if (input.length() == 1) {
                    if (word.contains(input)) {
                        for (int i = 0; i < word.length(); i++) {
                            if ((word.charAt(i) + "").equals(input)) {
                                newstatement = newstatement + word.charAt(i);
                            } else if (!(statement.charAt(i) == '-')) {
                                newstatement = newstatement + statement.charAt(i);
                            } else {
                                newstatement = newstatement + '-';
                            }
                        }
                        statement = newstatement;
                        System.out.println("That guess is correct. ");
                        if (statement.equals(word)) {
                            System.out.println("You have guessed the word. ");
                            System.out.println("The word was " + word + ". ");
                            System.out.println("Good job!");
                            System.out.println("Do you want to play again? (y/n)");
                            boolean inputGiven = false;
                            while (!inputGiven) {
                                input = (scanner.nextLine()).toUpperCase();
                                if (input.length() == 1) {
                                    if (input.equals("Y")) {
                                        playA = true;
                                        break;
                                    } else {
                                        System.out.println("ok bye.");
                                        playA = false;
                                    }
                                } else {
                                    System.out.println("give an actual input. ");
                                }
                            }
                            if (playA) {
                                beat = true;
                            }
                            else if (!playA) {
                                System.exit(0); //quit
                            }
                        }
                    } else {
                        System.out.println("There are no " + input.toUpperCase() + "'s in this word. ");
                        guesses = guesses - 1;
                        System.out.println("You have " + guesses + "guesses left. ");
                        if (guesses == 0) {
                            System.out.println("You have failed to guess the word. Better luck next time. ");
                            break;
                        }
                    }
                }
                else {
                    System.out.println("invalid input. ");
                }
            }
        }
    }





    public static String getRandomWord(String[] a) {

        Random rand = new Random();
        int upperbound = a.length;
        return a[rand.nextInt(upperbound)];


    }


} 

