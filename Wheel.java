public class Wheel{
    char[][] Wheel = new char[2][26];
    String[] WheelType1 = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ","AJDKSIRUXBLHWTMCQGZNPYFVOE"};
    String[] WheelType2 = {"BDFHJLCPRTXVZNYEIWGAKMUSQO","ESOVPZJAYQUIRHXLNFTGKDCMWB"};
    String[] WheelType3 = {"VZBRGITYUPSDNHLXAWMJQOFECK","JPGVOUMFYQBENHZRDKASXLICTW"};
    String[] WheelType4 = {"NZJHGRCXMYSWBOUFAIVLPEKQDT","FKQHTLXOCBJSPDZRAMEWNIUYGV"};
    String[] Reflector = {"EJMZALYXVBWFCRQUONTSPIKHGD"};
    String[] WheelType;
    public Wheel(int TypeWheel){
         Wheel = CreateWheel(TypeWheel);
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
            WheelType = Reflector;
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
    /*public int getWheelType(char[][] wheel){
        if(wheel[0][0] == 'e'){
            return 1;
        }
        else if(wheel[0][0] == 'b'){
            return 2;
        }
        else if(wheel[0][0] == 'v'){
            return 3;
        }
        else if(wheel[0][0] == 'n'){
            return 4;
        }
        else{
            return 0;
        }
    }*/
}