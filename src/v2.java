/*questions to ask:
are you allowed to guess a whole word?


 */
import java.util.Random;
import java.util.Scanner;

public class v2 {
    public static String word = "";
    public static String statement = "";
    public static String newstatement = "";
    public static String input = "";
    public static int guesses = 8;
    public static String[] wordList = {"HELLO", "GOODBYE", "PYTHAGORAS", "CHAIR", "BICUBIC", "MONITOR", "RECOMMENDATION", "MYOPIC", "VISION", "PROJECT"};

    public static void main(String[] args) {

        int guesses = 8;
        System.out.println("Hello, welcome to Hangman!!! :)");
        System.out.println("Input a letter to get started!");
        play(1); //play once as the user ran the code to play the game










    }

    public static void play(int a) {
        if (a == 1) { //1 means play, 0 means quit
            createWord(wordList);
            System.out.println(word); //delete for final
            for (int i = 0; i < word.length(); i++) { //create the ----, uses for loop to see how many - are needed
                statement = statement + "-";
            }
            Scanner scanner = new Scanner(System.in);
            boolean gameOver = false;
            while(!gameOver) { //while word has not been guessed
                newstatement = "";
                System.out.println(statement);
                input = scanner.nextLine();
                while (!checkValidInput(input)) { //in case of invalid input
                    System.out.println("Input any letter. ");
                    input = scanner.nextLine();
                }
                input = input.toUpperCase(); //so it's not case-sensitive
                if (word.contains(input)) {
                    updateWord(); //method doesnt need input as input is global variable
                    if (statement.equals(word)) {
                        gameOver = true;
                        System.out.println("Congratulations! You have beaten the game with " + guesses + " guesses left. ");
                        System.out.println("The word was " + word + ". ");
                    }
                    else {
                        System.out.println("Guess another letter. ");
                    }
                }
                else {
                    guesses = guesses -1;
                    if (guesses==0) {
                        System.out.println("You have lost the game. ");
                        System.out.println("The word was " + word + ". ");
                        gameOver = true;
                    }
                    else {
                        System.out.println("The letter " + input + " was not in the word. ");
                        System.out.println("You have " + guesses + " guesses remaining. ");
                        System.out.println("Input another letter. ");
                    }
                }


            }
            checkPlayAgain(); //asks if they want to play again and runs if yes

        }
        else {
            System.out.println("Thanks for playing! See you next time. ");
            System.exit(0);
        }


    }

    public static void createWord(String[] in) { //todo take file as input
        word = getRandomWord(in);
    }

    public static void checkPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again? (y/n)");
        input = scanner.nextLine();
        boolean chosen = false;
        while (!chosen) {
            if (!(input.length() == 1)) {
                System.out.println("Please input y/n. ");
            } else {
                if (input.equals("y")) {
                    chosen = true;
                    statement = ""; //reset word
                    guesses = 8; //reset guesses
                    play(1); //play again
                } else {
                    chosen = true;
                    play(0);
                }
            }
        }

    }

    public static void updateWord() { //letter is checked to be in the word already, updates to show word with the letter in it
        System.out.println("The word does contain an " + input + ". ");
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
    }

    public static boolean checkValidInput(String in) { //check if input is a letter, return t/f
        char[] chars = in.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }



    public static String getRandomWord(String[] a) {

        Random rand = new Random();
        int upperbound = a.length;
        return a[rand.nextInt(upperbound)];


    }

}
