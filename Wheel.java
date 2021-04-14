import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Wheel{
    char[][] Wheel = new char[2][26];

    String[] WheelType1;
    String[] WheelType2;
    String[] WheelType3;
    String[] WheelType4;
    String[] reflector;

    String[] WheelType;
    File file;
    Scanner scanner;
    
    public Wheel(int TypeWheel){
        String filename = "wheels.txt";
        
        try {
            file = new File(filename);
            scanner = new Scanner(file);
            String input = scanner.nextLine();

            //wheel creation, some will be chosen, some won't
            WheelType1 = input.split(" ");
            input = scanner.nextLine();
            WheelType2 = input.split(" ");
            input = scanner.nextLine();
            WheelType3 = input.split(" ");
            input = scanner.nextLine();
            WheelType4 = input.split(" ");
            input = scanner.nextLine();
            reflector = input.split(" ");

            Wheel = CreateWheel(TypeWheel);
            scanner.close();
        } catch (Exception e) {
            System.out.println("No file found");
            e.printStackTrace();
        }
    }

    //makes and returns the Wheel based on the input given in constructor
    public char[][] CreateWheel(int WheelChoice){
        if(WheelChoice == 0) {

            WheelType = WheelType1;
        }
        else if(WheelChoice == 1) {
            WheelType = WheelType2;
        } 
        else if (WheelChoice == 2) {
            WheelType = WheelType3;
        } 
        else if (WheelChoice == 3) {
            WheelType = WheelType4;
        }

        //1942 just as a code for using the reflector
        else if(WheelChoice == 1942){
            WheelType = reflector;
        }
        else{
            WheelType = WheelType1;
        }

            int WheelRow = 0;
            for(String WheelHalf : WheelType){
                for(int i = 0; i < WheelHalf.length(); i++){
                    Wheel[WheelRow][i] = WheelHalf.toLowerCase().charAt(i);
                }
                WheelRow ++;
            }
            return Wheel;

        } 

    //simply returns the wheel that was made
    public char[][] getWheel(){
        return Wheel;
    }

    public char[] getWheelSide(Direction side){
        if(side == Direction.LEFT){
            return Wheel[0];
        }
        else if(side == Direction.RIGHT){
            return Wheel[1];
        }
        else{
            return null;
        }
    }

}