//The Actual Enigma Device, includes the Keyboard and Lampboard
import java.util.*;

public class EnigmaDevice{

    public static void main(String[] args) {
        System.out.println("Check Rotor line 200 | Check Rotor line 47 (Rotate method in general)");
        System.out.println("Check switching of letter to capital unintentionally randomly");
        /*
           Creates the plugboard : Used to switch letters before going through the rotor
           Creates the Rotor : Used to rotate the wheels and will then change the outgoing letter
           Creates the Scanner : Used to get User Input
        */
        PlugBoard plugboard = new PlugBoard();
        Scanner input = new Scanner(System.in);
        boolean  playing = true;
        char[][] alphabet = {{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
                             {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}};
        
        ///CREATING WHEELS AND LOCATIONS///

            //The creation of the wheels
            int count = 1;
            int wheel1 = 0;
            int wheel2 = 0;
            int wheel3 = 0;
            Integer userInput = null;
            while(count < 4){
                System.out.println("\nChoose Wheels (" + count + "/3)\nWheel 1 : 1\nWheel 2 : 2\nWheel 3 : 3\nWheel 4 : 4\n");
                userInput = input.nextInt();
                while(userInput == null || userInput > 4 || userInput < 1){
                    System.out.println("\nInvalid answer Choose Wheels (" + count + "/3)\nWheel 1 : 1\nWheel 2 : 2\nWheel 3 : 3\nWheel 4 : 4\n");
                    userInput = input.nextInt();
                }
                //it switches which wheel is assigned
                switch(count){
                    case 1 :
                        wheel1 = userInput - 1;
                        break;
                    case 2 :
                        wheel2 = userInput - 1;
                        break;
                    case 3 :
                        wheel3 = userInput - 1;
                        break;
                }
                count ++;
            }

            //The placing of the wheels in locations
            count = 1;
            int locwheel1 = 0;
            int locwheel2 = 0;
            int locwheel3 = 0;
            boolean leftAvailable = true;
            boolean MiddleAvailable = true;
            boolean rightAvailable = true;
            String locAvailableLeft = "|1|";
            String locAvailableMiddle = "|2|";
            String locAvailableRight = "|3|";
            while(count < 4){
                System.out.println("\nChoose locations for wheels (" + count + "/3)\nLocations Available" + locAvailableLeft + " " + locAvailableMiddle + " " + locAvailableRight);
                userInput = input.nextInt();
                while(userInput == null || userInput > 3 || userInput < 1 || userInput == 1 && !leftAvailable || userInput == 2 && !MiddleAvailable || userInput == 3 && !rightAvailable){
                    System.out.println("\nInvalid Choice : Choose locations for wheels (" + count + "/3)\nLocations Available" + locAvailableLeft + " " + locAvailableMiddle + " " + locAvailableRight);
                    userInput = input.nextInt();
                }
                //Error is caused by reading a null value in the array
                    if(userInput - 1 == 0 && leftAvailable){
                        locAvailableLeft = "|FILLED|";
                        locwheel1 = userInput - 1;
                        leftAvailable = false;
                    }
                    else if(userInput - 1 == 1 && MiddleAvailable){
                        locAvailableMiddle = "|FILLED|";
                        locwheel2 = userInput - 1;
                        MiddleAvailable = false;
                        
                    }
                    else if(userInput - 1 == 2 && rightAvailable){
                        locAvailableRight = "|FILLED|";
                        locwheel3 = userInput - 1;
                        rightAvailable = false;
                        
                    }
                count ++;
            }
            Rotor rotor = new Rotor(wheel1, locwheel1, wheel2, locwheel2, wheel3, locwheel3);
            //While the User still wants to write messages run the program
            boolean firstTime = true;
        while(playing){     
///SWITCHING///
            String answer = "";
            System.out.print("\nWould you like to switch any letters? ");
            if(firstTime){
                input.nextLine();
                firstTime = false;
            }
            answer = input.nextLine();
            // If the Response is null or does not start with a 'y' or an 'n' the program will ask again
            while(answer.equals("") || !answer.substring(0,1).toUpperCase().equals("Y") && !answer.substring(0,1).toUpperCase().equals("N")){
                System.out.print("\nInvalid Response : Would you like to switch any letters? ");
                answer = input.nextLine();
            }

            // If the Response of the User is 'y'
            if(answer.substring(0,1).toUpperCase().equals("Y")){
                boolean keepSwitching = true;

                //If the User wants to switch any letters using the PlugBoard
                while(keepSwitching){
                    System.out.print("\nWhich 2 Letters would you like to switch? (Separate with a space): ");
                    String switchLetter = input.nextLine();

                    /* Error Handling if the length of the response is less than 3 
                        (letter + " " + letter)
                    */
                    while(switchLetter.length() != 3 || !switchLetter.substring(1,2).equals(" ") || !inAlph(switchLetter.charAt(0), alphabet) ||  !inAlph(switchLetter.charAt(2), alphabet)){
                        System.out.println("\nInvalid Response : Which 2 Letters would you like to switch? (Separate with a space): ");
                        switchLetter = input.nextLine();
                    }

                    //Grabs the letter at the beginning and end
                    char key1 = switchLetter.charAt(0);
                    char key2 = switchLetter.charAt(2);

                    /* Does not Actually switch Letters
                        Simply adds a # to the array 
                        for the scrambler to locate
                    */
                    plugboard.SwitchLetters(key1, key2);
                    System.out.print("Would you like to switch another pair? ");
                    answer = input.nextLine();
                    while(answer.substring(0,1).toUpperCase().equals("Y") && answer.substring(0,1).toUpperCase().equals("N")){
                        System.out.print("\nInvalid Response : Would you like to switch another pair? ");
                    answer = input.nextLine();
                    }
                                        
                    /* If the User would like to switch another pair 
                        it does nothing so the loop runs again
                    */
                    if(answer.substring(0,1).toUpperCase().equals("Y")){

                    }

                    /* If the User does not want to switch another pair of letters 
                        the program will move onto the message part of the program
                    */
                    else if(answer.substring(0,1).toUpperCase().equals("N")){
                        keepSwitching = false;
                    }
                }
            }
///MESSAGING///
        
            System.out.print("Enter Your Message : ");
            String unEncrypted = input.nextLine();
            
            // Line 69 Runs almost the entire program, Scrambling the message into something incomprehensible
            String encrypted = Scrambler(unEncrypted, rotor, plugboard, alphabet);

            // Prints out the Unencrypted version and the encrypted Version of the Message
            System.out.println("\n----------------------\n" + unEncrypted + "\n----------------------\n" + encrypted + "\n----------------------\n");
            encrypted = "";
            System.out.print("Would You Like to Write Another Message? ");
            answer = input.nextLine();
            
            // While User gives a null response or response doesn't start with 'y' or 'n'
            while(answer.equals("") || !answer.substring(0,1).toUpperCase().equals("Y") && !answer.substring(0,1).toUpperCase().equals("N")){
                System.out.print("\nInvalid Response : Would You Like to Write Another Message? ");
                answer = input.nextLine();
            }

            // If User says yes then it will repeat loop
            if(answer.substring(0,1).toUpperCase().equals("Y")){

            }

            // If User Says no it will break loop
            else if(answer.substring(0,1).toUpperCase().equals("N")){
                playing = false;
                break;
            }
        }
        input.close();
    }

    /**
     *  Scrambles the Message into something encrypted
     * @param unEncrypted A String Containing the original Message
     * @param rotor A Rotor Class containing the encrypting wheels
     * @param plugboard A PlugBoard Class for switching the letters
     * @return Returns the encrypted Word
     * */
    public static String Scrambler(String unEncrypted, Rotor rotor, PlugBoard plugboard, char[][] alphabet){
        String encrypted = "";
        for(int i = 0; i < unEncrypted.length(); i ++){
            char letter = unEncrypted.charAt(i);

            // Checks if the letter is in the Alphabet
            boolean posCharacter = inAlph(letter, alphabet);

            // If the character is a space then it will add it as a random letter
            
            /*if(letter == ' '){
                int row = (int)(Math.random()*2);  //lowercase or Uppercase
                int column = (int)(Math.random()*25); //Which letter
                encrypted += alphabet[row][column];
            } */

            // If the character isn't in the english alphabet just adds it without encrypting it
            
            /*else */if(!posCharacter){
                encrypted += letter;
            }

            // If the letter is in the alphabet will playing like normal, otherwise will just add the character
            else if(posCharacter){
                
                
                // Goes through the plugboard and switches letter if need be
                letter = SwitchLetters(plugboard, letter);

                // Method that scrambles the letter through the wheels
                letter = rotor.Scramble(letter, encrypted);

                // Goes through the plugboard again and switches the letter if need be
                letter = SwitchLetters(plugboard, letter);
                encrypted += letter;
                }   
        }
        return encrypted;
    }

    /**
     * 
     * @param plugboard PlugBoard class that holds whether or not the letter switches
     * @param letter The Unencrypted letter as a char that will be switched
     * @return The letter that may have been switched depending on the plugboard
     */
    public static char SwitchLetters(PlugBoard plugboard, char letter){
        for(int row = 0; row < 2; row ++){

            // If the plugboard does have a matching pair it will find it
            if(plugboard.getSwitch()[PlugBoard.FindLocation(letter)][row] > 0){
                int locSwitch = plugboard.getSwitch()[PlugBoard.FindLocation(letter)][row]; // The number pair of the letter in the plugboard array
                for(int r = 0; r < plugboard.getSwitch().length; r ++){
                
                // If the number pair matches with the letter grabbed and it isn't the letter itself
                    if(locSwitch == plugboard.getSwitch()[r][row] && PlugBoard.FindChar(r,(row != 0)) != letter){
                        letter = PlugBoard.FindChar(r,(row != 0)); // Changes the letter to the other pair
                        return letter;
                    }   
                }
            }
        }
        return letter;
    }

    /**
     * 
     * @param letter The letter that it is checking for in the alphabet
     * @param alphabet The 2D char array of the enlgish alphabet from a - Z
     * @return true if character is in alphabet, false otherwise
     */
    public static boolean inAlph(char letter, char[][] alphabet){
        boolean posCharacter = false;
        // Checks if the character is in the alphabet array
        for(char[] x : alphabet){
            for(char z : x){
                if(letter == z){
                    return posCharacter = true;
                }
            }
        }
        return posCharacter;
    }
}