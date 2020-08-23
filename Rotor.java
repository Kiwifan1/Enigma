public class Rotor{
    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;
    public static final int RANDOM = 4;
    
    //The entire wheels and the array I put them in
    private Wheel[] rotorWheel;
    private Wheel wheel1 ;
    private Wheel wheel2;
    private Wheel wheel3;
    private Wheel randWheel;
    //Tracks the individual parts of the wheel in movement
    private char[] outerLeftWheel;
    private char[] outerMiddleWheel;
    private char[] outerRightWheel;
    private char[] innerLeftWheel;
    private char[] innerMiddleWheel;
    private char[] innerRightWheel;
    private char[] reflector;
    //Tracks the location of the disk in rotation
    private int locLeft = 0;
    private int locMiddle = 0;
    private int locRight = 0;
    public Rotor(int wheelTypeLeft, int locLeft, int wheelTypeMiddle, int locMiddle, int wheelTypeRight, int locRight){
        
        rotorWheel = new Wheel[4];
        wheel1 = new Wheel(wheelTypeLeft);
        wheel2 = new Wheel(wheelTypeMiddle);
        wheel3 = new Wheel(wheelTypeRight);
        randWheel = new Wheel(RANDOM);
        //sets the left, middle, and right wheel to locations given by input
        rotorWheel[locLeft] = wheel1;
        rotorWheel[locMiddle] = wheel2;
        rotorWheel[locRight] = wheel3;
        rotorWheel[3] = randWheel;
        outerLeftWheel = rotorWheel[0].getWheel()[0];
        outerMiddleWheel = rotorWheel[1].getWheel()[0];
        outerRightWheel = rotorWheel[2].getWheel()[0];
        innerLeftWheel = rotorWheel[0].getWheel()[1];
        innerMiddleWheel = rotorWheel[1].getWheel()[1];
        innerRightWheel = rotorWheel[2].getWheel()[1];
        reflector = rotorWheel[3].getWheel()[0];
    }

    //method that rotates the wheels with each one before it requiring a full revolution before the next will turn
    public void Rotate(String word){        
        //double chance = Math.random();

        // checks if the word has mroe than 1 character left
        boolean large = false;
        if(word.length() > 1){
            large = true;
        }
        //shift all elements of the array for the Left Wheel
        char temp = outerLeftWheel[0]; // temp variable that is used for the first element for each wheel

        for(int i = 0; i < outerLeftWheel.length - 1; i ++){

            outerLeftWheel[i] = outerLeftWheel[i+1]; //moves elements to left

        }
            outerLeftWheel[outerLeftWheel.length - 1] = temp; //replaces last element with first
        //
        locLeft ++;

        if(large && word.charAt(0) == word.charAt(1) || locLeft == 12){

            temp = innerLeftWheel[0];
            for(int i = 0; i < innerLeftWheel.length - 1; i ++){

                innerLeftWheel[i] = innerLeftWheel[i+1]; //moves elements to left
    
            }
                innerLeftWheel[innerLeftWheel.length - 1] = temp; //replaces last element with first
        }

        //If the Left wheel has gone through an entire rotation shifts the middle wheel

        if(locLeft >= 2){
            locMiddle ++;

            //shifts all elements of the array for the Middle Wheel

            temp = outerMiddleWheel[0];

            for(int i = 0; i < outerMiddleWheel.length - 1; i ++){

                outerMiddleWheel[i] = outerMiddleWheel[i+1];
            }
                outerMiddleWheel[outerMiddleWheel.length - 1] = temp;

            locLeft = 0;
            if(large && word.charAt(0) == word.charAt(1) || locMiddle == 12){
                temp = innerMiddleWheel[0];

                for(int i = 0; i < innerMiddleWheel.length - 1; i ++){

                    innerMiddleWheel[i] = innerMiddleWheel[i+1];
                }
                    innerMiddleWheel[innerMiddleWheel.length - 1] = temp;
            }
        }

        //If the Middle wheel has gone through an entire rotation shifts the right wheel

        if(locMiddle >= 3){

            locRight ++;

            //shifts all elements of the array for the Right Wheel

            temp = outerRightWheel[0];

            for(int i = 0; i < outerRightWheel.length - 1; i ++){

                outerRightWheel[i] = outerRightWheel[i+1];
            }

            outerRightWheel[outerRightWheel.length - 1] = temp;
            if(large && word.charAt(0) == word.charAt(1) || locRight == 12){
                temp = innerRightWheel[0];

                for(int i = 0; i < innerRightWheel.length - 1; i ++){
    
                    innerRightWheel[i] = innerRightWheel[i+1];
                }
    
                innerRightWheel[innerRightWheel.length - 1] = temp;
            }
            locMiddle = 0;
        }
        // simply resets the locRight to 0
        if(locRight >= 25){

            locRight = 0;

        }
    }
    
    /**
     * 
     * @param orig is the original letter inputted into the method
     * @param word the encrypted letter that will 
     * @return 
     */
    public char Scramble(char orig, String word){
        char encrypted = orig;
        boolean capital = false;
        encrypted = Character.toLowerCase(encrypted);
        if(orig != encrypted){
            capital = true;
        }   
        int index = 0;
        word += orig;
        //finds the index of the letter put in unencrypted in the outer left wheel (every loop does this)

        for(char i : outerLeftWheel){
            if(encrypted == i || index == 25){
                break;
            }
            index ++;
        }

        //changes the letter to the letter at the same index of the original letter
        encrypted = innerLeftWheel[index];
        Rotate(word);
        

        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : outerMiddleWheel){
            if(encrypted == i || index == 25){
                
                break;
            }
            index ++;
        }
        //rotates and overwrites encrypted letter
        encrypted = innerMiddleWheel[index];
        Rotate(word);
        

        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : outerRightWheel){
            if(encrypted == i || index == 25){
                
                break;
            }
            index ++;
        }
        //rotates and overwrites encrypted letter
        encrypted = innerRightWheel[index];
        Rotate(word);
        
        //letter has reached end of wheels and will now go backwards through the same process
        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : reflector){
            if(encrypted == i || index == 25){
                
                break;
            }
            index ++;
        }
        //rotates and overwrites encrypted letter
        encrypted = reflector[index];
        Rotate(word);

        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : innerRightWheel){
            if(encrypted == i || index == 25){
                
                break;
            }
            index ++;
        }
        //rotates and overwrites encrypted letter
        encrypted = outerRightWheel[index];
        Rotate(word);
        
        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : innerMiddleWheel){
            if(encrypted == i || index == 25){
                
                break;
            }
            index ++;
        }
        //rotates and overwrites encrypted letter
        encrypted = outerMiddleWheel[index];
        Rotate(word);
        
        index = 0;
        //finds the index of the encrypted letter and changes it to the letter at the same index
        for(char i : innerLeftWheel){
            if(encrypted == i){
                break;
            }
            index ++;
        }
        
        if(index > 25){
            index -= 26;
        }
        
        //rotates and overwrites encrypted letter
        encrypted = outerLeftWheel[index];
        Rotate(word);

        if(capital){
            encrypted = Character.toUpperCase(encrypted);
        }
        //returns final encrypted letter
        return encrypted;
    }
}