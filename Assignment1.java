import java.util.Arrays; // Importing the Arrays class
import java.util.Scanner; // Importing the Scanner class

// Programming Assignment Unit_1 --- A simple Quiz Game

public class Assignment1 {
    // Create a function to convert string value to char and add to the userAnswers array
    public static char[] AddToCharArray(char[] oldArray, String newString) {

        char[] newArray = Arrays.copyOf(oldArray, oldArray.length + 1);
        char newChar = Character.toUpperCase(newString.charAt(0));
        newArray[oldArray.length] = newChar;
        return newArray;

    }


    public static void main(String[] args) {

        // Define Quiz questions and put them in an array
        String Question_1 = "What data type is the value 'c' ?";  // char
        String Question_2 = "Who is the inventor of the java programming laguage?"; // james gosling
        String Question_3 = "In operator precedence, which operator is least in the precedence ?"; //subtraction
        String Question_4 = "An example of reference data type is ?"; // array
        String Question_5 = "What data type is the value True ?"; //boolean
        String[] questions = {
            Question_1, Question_2, Question_3, Question_4, Question_5
        };

        // Define Quiz options in an array
        String[] options = {
            "\n A. Int \n B. Char \n C. double \n D. float",
            "\n A. Guido Van Rossum \n B. Tim Berbers-Lee \n C. Brendan Eich \n D. James Gosling",
            "\n A. Division \n B. Multiplication \n C. Subtraction \n D. Addition",
            "\n A. Array \n B. Int \n C. Boolean \n D. Char",
            "\n A. Boolean \n B. String \n C. Float \n D. Classes"
        };

        // Define Correct Options in an array
        char[] correctOptions = { 'B', 'D', 'C', 'A', 'A' };

        // Create variables for use
        int total_score = 0; 
        char[] userAnswers =  {}; 
        

        // Create a Scanner Object to read user input
        Scanner userPick = new Scanner(System.in);
        
        System.out.println("\n CHOOSE FROM A TO D THE CORRECT OPTION FOR EACH QUESTION \n");
        
        // Display question and options for user to choose using a for-loop
        for (int i = 0; i < 5; i++) {
                System.out.printf("Question_%d: " + questions[i] + options[i] + "\n", (i+1)); 
                String user_pick = userPick.nextLine(); // read user response

                // check if response is empty or more than one value
                while (user_pick.length() > 1 || user_pick.isEmpty()){
                    System.out.println("\n----- INPUT AN OPTION FOR THIS QUESTION! -----");
                    System.out.printf("Question_%d: " + questions[i] + options[i] + "\n", (i+1)); 
                    user_pick = userPick.nextLine();
                }

                userAnswers = AddToCharArray(userAnswers, user_pick); // add user responser to userAnswers array
                if(userAnswers[i] == correctOptions[i]) { // check if user answer is correct
                
                    total_score += 1; // score user one point if correct
                }
                
        }; 
        
        double final_score = (double) total_score / 5 * 100; // calculate and score user
        System.out.println("You scored a total of: " + final_score + "%");  // Display Score of the User
        // System.out.println(Arrays.toString(userAnswers)); //Use in testcase to show user's Responses 
 
        // close the scanner object
        userPick.close();
    }
}