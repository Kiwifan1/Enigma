public class Wheel{
    char[][] Wheel;
    public Wheel(int TypeWheel){
         Wheel = CreateWheel(TypeWheel);
    }

    //makes and returns the Wheel based on the input given in constructor
    public char[][] CreateWheel(int TypeWheel){
        if(TypeWheel == 1){
            char[][] Wheel = {{'a', 'b', 'c', 'd','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
                              {'x','y','z','a','b','c','d','e','f','g','h','o','j','k','l','m','n','o','p','q','r','s','t','u','v','w'}};
            return Wheel;
        }
        else if(TypeWheel == 2){
            char[][] Wheel = {{'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j'},
                              {'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f'}};
            
            return Wheel;
        }
        else if(TypeWheel == 3){
            char[][] Wheel = {{'p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'},
                              {'s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r'}};
            
            return Wheel;
        }
        else if(TypeWheel == 4){
            char[][] Wheel = {{'m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l'}};
            return Wheel;
        }
        else{
            double RandNum = Math.random();
            //for testing purposes
            //System.out.println("RandNum : " + RandNum);
            //
            if(RandNum <= .25){
                char[][] Wheel = {{'a', 'b', 'c', 'd','e','f','g','h','o','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
                                  {'n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m'}};
            
                return Wheel;
            }
            else if(RandNum <= .5){
                char[][] Wheel = {{'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j'},
                                  {'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h'}};
            
                return Wheel;
            }
            else if(RandNum <= .75){
                char[][] Wheel = {{'p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'},
                                  {'t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'}};
            
                return Wheel;
            }
            else{
                char[][] Wheel = {{'x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w'},
                                  {'y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x'}};
            
                return Wheel;
            }  
        }
        
    }
    //simply returns the wheel that was made
    public char[][] getWheel(){
        return Wheel;
    }
}