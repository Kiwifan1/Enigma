import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Rotor{
    public static final int LEFT = 1;
    public static final int MIDDLE = 2;
    public static final int RIGHT = 3;
    public static final int RANDOM = 4;
    public static final int SECRET_ROTATING_NUM = 8; //this number has to be smaller than WHEEL_SHIFTER
    public static final int WHEEL_SHIFTER = 16;

    //NOTE: to completely replicate the enigma encryption process, each wheel would have its own rotating index and wheel shifter, this can be hardcoded easily and will probably do that later

    
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
    //for overwriting file
    File file;

    public Rotor(int wheelTypeLeft, int LocationLeft, int wheelTypeMiddle, int LocationMiddle, int wheelTypeRight, int LocationRight){
        String filename = "wheels.txt";
        file = new File(filename);

        rotorWheel = new Wheel[4];
        wheel1 = new Wheel(wheelTypeLeft);
        wheel2 = new Wheel(wheelTypeMiddle);
        wheel3 = new Wheel(wheelTypeRight);
        randWheel = new Wheel(RANDOM);

        //sets the left, middle, and right wheel to locations given by input
        rotorWheel[LocationLeft] = wheel1;
        rotorWheel[LocationMiddle] = wheel2;
        rotorWheel[LocationRight] = wheel3;
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

        // checks if the word has more than 1 character left
        //shift all elements of the array for the Left Wheel
        char temp = outerLeftWheel[0]; // temp variable that is used for the first element for each wheel

        for(int i = 0; i < outerLeftWheel.length - 1; i ++){

            outerLeftWheel[i] = outerLeftWheel[i+1]; //moves elements to left

        }
            outerLeftWheel[outerLeftWheel.length - 1] = temp; //replaces last element with first
        //
        locLeft ++;

        if(locLeft == SECRET_ROTATING_NUM){

            temp = innerLeftWheel[0];
            for(int i = 0; i < innerLeftWheel.length - 1; i ++){

                innerLeftWheel[i] = innerLeftWheel[i+1]; //moves elements to left
    
            }
            innerLeftWheel[innerLeftWheel.length - 1] = temp; //replaces last element with first
            
        }
        overwriteWheel(outerLeftWheel, innerLeftWheel);

        //If the Left wheel has gone through an entire rotation shifts the middle wheel

        if(locLeft >= WHEEL_SHIFTER){
            locMiddle ++;
            locLeft = 0;
            //shifts all elements of the array for the Middle Wheel

            temp = outerMiddleWheel[0];

            for(int i = 0; i < outerMiddleWheel.length - 1; i ++){
                outerMiddleWheel[i] = outerMiddleWheel[i+1];
            }

            outerMiddleWheel[outerMiddleWheel.length - 1] = temp;

            if(locMiddle == SECRET_ROTATING_NUM){
                temp = innerMiddleWheel[0];
                for(int i = 0; i < innerMiddleWheel.length - 1; i ++){
                    innerMiddleWheel[i] = innerMiddleWheel[i+1];
                }
                innerMiddleWheel[innerMiddleWheel.length - 1] = temp;
               
            }
            overwriteWheel(outerMiddleWheel, innerMiddleWheel);
        }

        //If the Middle wheel has gone through an entire rotation shifts the right wheel

        if(locMiddle >= WHEEL_SHIFTER){

            locRight ++;

            //shifts all elements of the array for the Right Wheel

            temp = outerRightWheel[0];

            for(int i = 0; i < outerRightWheel.length - 1; i ++){

                outerRightWheel[i] = outerRightWheel[i+1];
            }

            outerRightWheel[outerRightWheel.length - 1] = temp;

            if(locRight == SECRET_ROTATING_NUM){
                temp = innerRightWheel[0];

                for(int i = 0; i < innerRightWheel.length - 1; i ++){
    
                    innerRightWheel[i] = innerRightWheel[i+1];
                }
    
                innerRightWheel[innerRightWheel.length - 1] = temp;
                
            }
            locMiddle = 0;
            overwriteWheel(outerRightWheel, innerRightWheel);
        }
        // simply resets the locRight to 0
        if(locRight >= WHEEL_SHIFTER){

            locRight = 0;

        }
    }

    public void overwriteWheel(char[] leftArr, char[] rightArr){
        try {

            BufferedReader file = new BufferedReader(new FileReader("wheels.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line1;
            String line2;
            String leftSide = new String(leftArr).toUpperCase();
            String rightSide = new String(rightArr).toUpperCase();
            String correctedLine = "";
            boolean flag = true;

            file.mark(26);
            while(flag){
                if((line1 = file.readLine()).contains(leftSide.substring(1,leftSide.length() - 1)) && flag) {
                    //(line2 = file.readLine()).contains(rightSide.substring(1, rightSide.length() - 1))
                    line1 = leftSide;
                    line2 = rightSide;
                    correctedLine = line1 + " " + line2 + "\n";        
                    flag = false;
                }
            }

            file.reset();
            while((line1 = file.readLine()) != null){
                if(!(line1.contains(leftSide.substring(1, leftSide.length() - 1)))){
                    inputBuffer.append(line1 + "\n");
                }
                else{
                    inputBuffer.append(correctedLine);
                }
            }
            file.close();
    
            FileOutputStream fileOut = new FileOutputStream("wheels.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
    
        } catch (Exception e) {
            System.out.println("Problem reading file.");
            e.printStackTrace();
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