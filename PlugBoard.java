public class PlugBoard{
    private int[][] KeySwitch = new int[26][2];
    
    private int PairingNumber = 1;
    public PlugBoard(){
    /*
       0   [a] [n]  13      0   [A] [N]  13 
       1   [b] [o]  14      1   [B] [O]  14 
       2   [c] [p]  15      2   [C] [P]  15
       3   [d] [q]  16      3   [D] [Q]  16
       4   [e] [r]  17      4   [E] [R]  17
       5   [f] [s]  18      5   [F] [S]  18
       6   [g] [t]  19      6   [G] [T]  19
       7   [h] [u]  20      7   [H] [U]  20 
       8   [i] [v]  21      8   [I] [V]  21 
       9   [j] [w]  22      9   [J] [W]  22
       10  [k] [x]  23      10  [K] [X]  23
       11  [l] [y]  24      11  [L] [Y]  24
       12  [m] [z]  25      12  [M] [Z]  25
    */
    }
    
    //returns the 2 characters that were switched as a string;
    public String SwitchLetters(char key1, char key2){
        int index1 = FindLocation(key1);
        int index2 = FindLocation(key2); 
        if(index1 > KeySwitch[0].length){
            KeySwitch[index1][1] = PairingNumber;
        }
        else{
            KeySwitch[index1][0] = PairingNumber;
        }
        if(index2 > KeySwitch[0].length){
            KeySwitch[index2][1] = PairingNumber;
        }
        else{
            KeySwitch[index2][0] = PairingNumber;
        }
        String keys = key1 + " " + key2;
        PairingNumber ++;
        return keys;
    }
    //Returns the KeySwitch int array
    public int[][] getSwitch(){
        return KeySwitch;
    }
    //Gives the Location of the character in the array,  Trying to figure out how to decrease size,

    public static int FindLocation(char target){
        char[][] alphabet = {{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
                             {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}};
        int index = 0;
        for(char[] i : alphabet){
            for(char k : i){
                if(target == k){
                    return index;
                }
                else if(index == 25){
                    index = 0;
                }
                index ++;
            }
        }
        System.out.println("\n Error: FindLocation");
        return -1;
    }
    
    //does the exact opposite of the FindLocation Method, given an index will return the correct character
    public static char FindChar(int index, boolean Cap){
        if(!Cap){
            index += 97;
            return (char) index;
        }
        else{
            index += 65;
            return (char) index;
        }
        
    }
}