
package com.solera;

import java.util.InputMismatchException;
import java.util.Scanner;


public class BingoGame {
    
   
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numOfBingoPlayers=0;
        int callCounter=0;
        boolean input=false;
        
       
        while(true||input){
            try{
                System.out.print("Enter number of players(1-3): ");
               
                numOfBingoPlayers = in.nextInt();
                if(!(numOfBingoPlayers<1 ||numOfBingoPlayers>3)){
                    input=true;
                    break;
                } 
                System.out.println("Please follow directions!  "
                        + "Enter a single digit(1-3)!");
            }
           
            catch (InputMismatchException ime) {
               
                in.skip(".*"); 
                System.out.println("Please follow directions!  "
                        + "Enter a single digit(1-3)!");
            }
        }
       
        while(true ||input){
            try{
                System.out.print("Enter how many calls you want "
                        + "\nto make(4-75) before the game ends without a "
                        + "winner: ");
                callCounter = in.nextInt();
                if(!(callCounter<4 ||callCounter>75)){
                    input=true;
                    break;
                }
                System.out.println("Please follow directions!  "
                        + "Enter an integer(4-75)!");
            }
            
            catch (InputMismatchException ime) {
               
                in.skip(".*");
                System.out.println("Please follow directions! "
                    + " Enter an integet(4-75)!");
            }
        }
        
        BingoController bingoMaster = new BingoController(numOfBingoPlayers,
                callCounter);
       
        bingoMaster.generateNumberofCards();
        
        bingoMaster.playGame(); 
    }
    
}
