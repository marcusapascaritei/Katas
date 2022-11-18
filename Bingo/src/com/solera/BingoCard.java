
package com.solera;

import java.util.Random;



public class BingoCard{

    private static final int ROWS=5;
    private static final int COLS=5;
    public int[][] bCard;
    Random generator;
    
    
    public BingoCard(){
        this.generator = new Random();
        bCard = new int[ROWS][COLS];
    }
    
    
    protected void generateBingoCard(){
       
        generateColumnNumbers(0,1); 
       
        generateColumnNumbers(1,16);  
       
        generateColumnNumbers(2,31); 
     
        generateColumnNumbers(3,46); 
       
        generateColumnNumbers(4,61); 
    }
    
          
    protected void toString(int playerNumber){
	System.out.println("\n\tPlayer "+playerNumber+":");
	System.out.println("\tB    \tI    \tN    \tG    \tO");
	for (int[] bCard1 : bCard) {
            for (int j = 0; j<bCard[0].length; j++) {
               
                System.out.print("\t" + bCard1[j]);
            }
            System.out.print("\n");
        }
    }
    
  
    protected void markCard (int callingNumber){
        char bingoColumn;
        if(callingNumber<=15)
            bingoColumn = 'B';
        else if (callingNumber<=30)
            bingoColumn = 'I';
        else if (callingNumber<=45)
            bingoColumn = 'N';
        else if (callingNumber<=60)
            bingoColumn = 'G';
        else 
            bingoColumn = 'O';
        
      
        switch(bingoColumn) {
            case 'B':
                for (int[] bCard1 : bCard) {
                    if (callingNumber == bCard1[0]) {
                       
                        bCard1[0] = 0;
                    }
                }
                break;				
            case 'I':
                for (int[] bCard1 : bCard) {
                    if (callingNumber == bCard1[1]) {
                        
                        bCard1[1] = 0;
                    }
                }
                break;
	    case 'N':
                for (int[] bCard1 : bCard) {
                    if (callingNumber == bCard1[2]) {
                       
                        bCard1[2] = 0;
                    }
                }
                break;
            case 'G':
                for (int[] bCard1 : bCard) {
                    if (callingNumber == bCard1[3]) {
                        
                        bCard1[3] = 0;
                    }
                }
                break;
            case 'O':
                for (int[] bCard1 : bCard) {
                    if (callingNumber == bCard1[4]) {
                        
                        bCard1[4] = 0;
                    }
                }
                break;    
        }
    }
    
    
    protected boolean checkForWinningCard(){
        boolean playerWin = false;
        
        int columnSum;
        for (int i = 0;  i < COLS;  i++){
            columnSum=0;          
            for (int j = 0;  j < ROWS;  j++){
                columnSum += this.bCard[j][i];
            }
            if (columnSum == 0){
                playerWin = true;
            }
        }
       
        int rowSum;
        for (int i = 0;  i < ROWS;  i++){
            rowSum=0;
            for (int j = 0;  j < COLS;  j++){
                rowSum += this.bCard[i][j];
            }
            if (rowSum == 0){
                playerWin = true;
            }
        }
        
        
        int sumMainDiagonal =0;
        for (int i = 0; i < bCard.length; i++) {
            sumMainDiagonal += bCard[i][i];
        }
        if(sumMainDiagonal==0){
            playerWin = true;
        }
        
       
        int sumAntiDiagonal =0;
        int j = COLS - 1;
        for (int i = 0; i < COLS; i++) {
            if (j >= 0) {
                sumAntiDiagonal += bCard[i][j];
                    j--;
            }
        }
        if(sumAntiDiagonal == 0){
            playerWin = true;
        }
        return playerWin;
    }
            
    
    private boolean isADuplicate(int colNumber, int input){
        boolean duplicate = false;
        for(int i = 0; i<COLS;i++){
            if(bCard[i][colNumber]== input){
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
    
    
    private void generateColumnNumbers(int columnNumber, int randMin){
        boolean duplicate=true;
        int bNumber=0;         
        for(int i=0; i<COLS;i++){                    
            do{               
                bNumber = generator.nextInt(15) + randMin;
                duplicate = isADuplicate(columnNumber,bNumber);
            }
            while(duplicate);
            if(i==2 && columnNumber ==2){               
                bCard[i][columnNumber] = 0;
            }
            else{              
                bCard[i][columnNumber] = bNumber;
            }                     
        }
    }
}
