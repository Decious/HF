package SeaBattle;


public class SimpleDotComTestDrive {
    public static void main(String[] args) {
        int numOfGuesses=0;
        DotCom dot = new DotCom();
        
        int randomNum = (int)(Math.random()*5);
        int[] locations = {randomNum, randomNum+1 ,randomNum+2};
    //    dot.setLocationCells(locations);
        boolean isAlive= true;
        
        while (isAlive){
            String guess = GameHelper.getUserInput("Enter NUM");
            String result = dot.checkYourself(guess);
            numOfGuesses++;
            if (result.equals("GotONE!")){
                isAlive=false;
                System.out.println("You needed " + numOfGuesses + " Guesses");
                
            }
        }
            

    }
}
