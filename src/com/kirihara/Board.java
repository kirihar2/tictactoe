package com.kirihara;

import java.util.*;

public class Board {
    private List<List<String>> gameBoard;
    private int boardSize;
    private String winner;
    public Board(){
        this.gameBoard = new ArrayList<>();
        this.boardSize = 3; //can remove this and put in as constructor to make n sized board
        for (int row=0; row< this.boardSize ; row++){
            List<String> gameBoardRow = new ArrayList<>();
            for (int col =0 ; col < this.boardSize; col++) {
                gameBoardRow.add("");
            }
            this.gameBoard.add(gameBoardRow);
        }
        this.winner="";
    }

    public void printWinner(){
        if (!this.winner.isEmpty()) System.out.println(String.format("Player %s won!!!",winner));

    }

    public boolean isGameDone(){
        //check horizontal
        for (int row=0; row< this.boardSize ; row++){
            List<String> gameBoardRow = this.gameBoard.get(row);
            String potentialWinner = gameBoardRow.get(0);
            if (potentialWinner.isEmpty()) continue;
            for (int col =1 ; col < this.boardSize; col++) {
                String currCell = gameBoardRow.get(col);
                if (!currCell.equals(potentialWinner)) break;
                if(col==this.boardSize-1) {
                    this.winner=potentialWinner;
                    return true;
                }
            }
        }
        //check vertical
        for (int col=0; col< this.boardSize ; col++){
            String potentialWinner = this.gameBoard.get(0).get(col);
            if (potentialWinner.isEmpty()) continue;
            for (int row =1 ; row < this.boardSize; row++) {
                String currCell = this.gameBoard.get(row).get(col);
                if (!currCell.equals(potentialWinner)) break;
                if(row==this.boardSize-1) {
                    this.winner=potentialWinner;
                    return true;
                }
            }
        }
        //check diagonal left to right
        String potentialWinner = this.gameBoard.get(0).get(0);
        if (!potentialWinner.isEmpty()) {
            for (int row=1; row< this.boardSize ; row++){
                String currCell = this.gameBoard.get(row).get(row);
                if (!currCell.equals(potentialWinner)) break;
                if(row==this.boardSize-1) {
                    this.winner=potentialWinner;
                    return true;
                }
            }
        }
        //check diagonal right to left
        potentialWinner = this.gameBoard.get(this.boardSize-1).get(0);
        if (!potentialWinner.isEmpty()) {
            for (int row=1; row< this.boardSize ; row++){
                String currCell = this.gameBoard.get(this.boardSize-1-row).get(row);
                if (!currCell.equals(potentialWinner)) break;
                if(row==this.boardSize-1) {
                    this.winner=potentialWinner;
                    return true;
                }
            }
        }
        return false;
    }
    public void movePlayerToLocation(Player p, int row, int col){
        this.gameBoard.get(row).set(col,p.getPlayerPiece());
    }
    //TODO keep track of attempted values
    public int [] getRandomOpenLocation(){
        int [] ret = new int[2];
        Random ran = new Random();
        do{
            ret[0]= ran.nextInt(this.getBoardSize()) + 1;
            ret[1]= ran.nextInt(this.getBoardSize()) + 1;
        } while(!canMove(ret[0]-1,ret[1]-1));
        return ret;
    }

    public boolean canMove(int row, int col){
        return  (row<=this.boardSize && row >=0)
                && (col<= this.boardSize && col>=0)
                && (this.gameBoard.get(row).get(col).equals(""));
    }
    //TODO create getter and setter for the gameboard, also create a copy constructor in case multiple boards needs to be created

    public int getBoardSize(){
        return this.boardSize;
    }
}
