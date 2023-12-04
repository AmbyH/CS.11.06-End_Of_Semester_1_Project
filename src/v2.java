/*questions to ask:
are you allowed to guess a whole word?


 */
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class v2 {
    public static String word = "";
    public static String statement = "";
    public static String newstatement = "";
    public static String input = "";
    public static int guesses = 8;
    public static String[] wordList = {"HELLO", "GOODBYE", "PYTHAGORAS", "CHAIR", "BICUBIC", "MONITOR", "RECOMMENDATION", "MYOPIC", "VISION", "PROJECT"};

    public v2() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to Hangman!!! :)");
        System.out.println("Input a letter to get started!");
        System.out.println("Would you like to add a custom word into the list? (y/n)");
        boolean addWords = false;
        input = scanner.nextLine();
        while (!checkValidInput(input)) {
            System.out.println("Please input (y/n)");
        }
        while (!addWords) {
            if (input.equals("y")) {
                System.out.println("What do you want to input? (This change will be permanent)");
                input = scanner.nextLine();
                if(binarySearch(input)) {
                    System.out.println("This word is already in the list!");
                }
                else {
                    sortWriteFile("HangmanWordsList.txt");
                }
                System.out.println("Do you want to add more words? (y/n)");
                input = scanner.nextLine();
                while (!checkValidInput(input)) {
                    System.out.println("Please input (y/n)");
                }
                if (input.equals("y")) {
                    addWords = false;
                }
                else {
                    addWords = true;
                }
            }
            else {
                addWords = true;
            }

        }
        play(1);











    }

    public static void play(int a) throws FileNotFoundException {
        if (a == 1) { //1 means play, 0 means quit
            word = createWord("HangmanWordsList.txt");
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

    public static String createWord(String in) throws FileNotFoundException { //todo take file as input
        //word = getRandomWord(in);
        return getRandomWord(readStrFile(in));
    }

    public static void checkPlayAgain() throws FileNotFoundException {
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

    private static String[] readStrFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        String[] data = new String[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();
        return data;
    }

    private static String[] readStrFileAdd(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename) + 1;
        String[] data = new String[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextLine();
        }
        scanner.close();
        data[numberOfLinesInFile-1] = input.toUpperCase();
        return data;
    }

    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }

    public static boolean findInFile(String outPutFilename) throws IOException { //todo binary search
        word = input.toUpperCase();
        File file = new File(outPutFilename);
        int numberOfLinesInFile = countLinesInFile(outPutFilename);
        System.out.println(numberOfLinesInFile);
        for (int i = 0; i<numberOfLinesInFile;i++) {
            System.out.println(i);
            if (readStrFile("HangmanWordsList.txt")[i].equals(word)) {
                return true;
            }
        }
        return false;
    }
    public static boolean binarySearch(String target) throws FileNotFoundException {
        String[] words = readStrFile("HangmanWordsList.txt");
        int left = 0;
        int right = words.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            int comparison = target.compareTo(words[middle]);

            if (comparison == 0) {
                return true; // Word found at the middle index
            } else if (comparison < 0) {
                right = middle - 1; // Search the left half of the array
            } else {
                left = middle + 1; // Search the right half of the array
            }
        }

        return false; // Word not found in the array
    }
    public static void sortWriteFile(String filePath) throws FileNotFoundException {
        String[] temp = readStrFileAdd("HangmanWordsList.txt");
        // Sort the array alphabetically
        Arrays.sort(temp);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (String word : temp) {
                writer.write(word);
                writer.newLine();
            }
            writer.close();

            System.out.println("Word written added successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
