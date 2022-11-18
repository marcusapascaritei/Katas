package com.solera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class BingoController {
     protected int numOfBingoPlayers;
    private BingoCard [] bingoCard;
    private Random generator;  
    private ArrayList<Integer> callNumberList;
    private ArrayList<Integer> winnerList;
    private ArrayList<String> numbersCalled;
    private int firstPrint = 0;
    private int callCounter;
    
   
    private void addBingoNumbers(){
        for(int i=1;i<76;i++){
            callNumberList.add(i);
        }
    }
        
  
    public BingoController(int numOfBingoPlayers, int callCounter){
        this.numOfBingoPlayers=numOfBingoPlayers;
        this.callCounter=callCounter;
        this.generator = new Random();
        this.callNumberList = new ArrayList<>();
        this.winnerList = new ArrayList<>();
        this.numbersCalled = new ArrayList<>();        
        this.bingoCard = new BingoCard[numOfBingoPlayers];          
    }
    
    
    protected void generateNumberofCards(){
        for(int i = 0; i < numOfBingoPlayers; i++){
            bingoCard[i] = new BingoCard();
            bingoCard[i].generateBingoCard();
        }        
    }
    

    private void printOutCards(){
        if(firstPrint==0){
            System.out.println("\nInitial Cards:");
        }
        else{
            System.out.println("\nFinal Cards:");
        }
        for(int i = 0;i<numOfBingoPlayers;i++){               
            bingoCard[i].toString(i);
        }
        firstPrint++;
    }
    

    private void checkForWinners(){

        boolean endGame=true;

        int callNumber =0;

        int winList = 0;
        boolean winner=false;
 
        int numOfCalls = 0;
        do{
         
            callNumber = generateCalls(callNumberList);
            numOfCalls++;
            for(int i = 0;i<numOfBingoPlayers;i++){

                bingoCard[i].markCard(callNumber);
            }          
            if(numOfCalls>=4){
                for(int i = 0;i<numOfBingoPlayers;i++){     
           
                    winner = bingoCard[i].checkForWinningCard();
                    if(winner==true){
                        winnerList.add(i);
                        winList++;
                    }
                }
           
                if(winList>0){
                    endGame = false;
                }
             
                if(winList==0 && numOfCalls>callCounter){
                    endGame = false;
                }
            }                      
        }
        while(endGame);
    }
    
    
    private void printWinners(){
        if(winnerList.isEmpty()){
            System.out.println("Winner: No winner:");
        }
        else{
            for (Integer winnerList1 : winnerList) {
                System.out.println("\nWinner: Player " + winnerList1);
            }
        }
    }
    
    private int generateCalls(ArrayList<Integer> someArrayList){
        
        Collections.shuffle(someArrayList);
        
        int index = generator.nextInt(someArrayList.size());
        int bingoNumber = someArrayList.get(index);
        
        
        char bingoColumn;
        if(bingoNumber<=15)
            bingoColumn = 'B';
        else if (bingoNumber<=30)
            bingoColumn = 'I';
        else if (bingoNumber<=45)
            bingoColumn = 'N';
        else if (bingoNumber<=60)
            bingoColumn = 'G';
        else 
            bingoColumn = 'O';
        

        switch(bingoColumn) {
            case 'B':
                numbersCalled.add("B"+bingoNumber);                                     
                break;  
            case 'I':
                numbersCalled.add("I"+bingoNumber);
                break;
	    case 'N':
                numbersCalled.add("N"+bingoNumber);
                    break;
            case 'G':
                numbersCalled.add("G"+bingoNumber);
                    break;
            case 'O':
                numbersCalled.add("O"+bingoNumber);
                    break;  
        }
       
        someArrayList.remove(index);
        return bingoNumber;
        
    }
    
    
    protected void playGame(){
       
        this.generateNumberofCards();
       
        this.printOutCards();
        
        this.addBingoNumbers();
        
        this.checkForWinners();
       
        this.printOutCalls();
        
        this.printOutCards();
        
        this.printWinners();  
        
    }
    
    
    private void printOutCalls(){
       System.out.print("\nCalls: "); 
       for(int i=1; i<numbersCalled.size();i++){
           
           System.out.print(numbersCalled.get(i));
           if(i<numbersCalled.size()-1){
           System.out.print(",");
           }
       } 
    }
}
