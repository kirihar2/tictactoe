package com.kirihara;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX;
    private Player playerO;

    public Game(){
        this.board = new Board();
        this.playerX = new Player("X",true);
        this.playerO = new Player("O",false);
    }

    public void startGame(){
        System.out.print("Would you like to start? (y/n): ");
        try (Scanner in = new Scanner(System.in)){
            String isGoingFirst = in.next();
            boolean userGoingFirst = isGoingFirst.toLowerCase().equals("y");
            Player currentPlayer = (userGoingFirst) ? playerX: playerO;
            Player nextPlayer = (userGoingFirst) ? playerO: playerX;
            while (!board.isGameDone()){
                int row, col;
                if(currentPlayer.isUser()) {
                    //loop to validate input
                    while(true) {
                        try {
                            System.out.print(String.format("Player %s your move: \nX coordinate: ", currentPlayer.getPlayerPiece()));
                            col = in.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println(String.format("Please enter valid number between 1 and %d: \n", board.getBoardSize()));
                        }
                    }
                    while(true){
                        try {
                            System.out.print(String.format("Y coordinate: ",currentPlayer.getPlayerPiece()));
                            row = in.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println(String.format("Please enter valid number between 1 and %d: \n", board.getBoardSize()));
                        }
                    }
                } else{ //Computer is making a move
                    System.out.println(String.format("Player %s is making their move!",currentPlayer.getPlayerPiece()));
                    int[] location = board.getRandomOpenLocation();
                    row = location[0];
                    col = location[1];
                    System.out.println(String.format("X coordinate %s ",String.valueOf(col)));
                    System.out.println(String.format("Y coordinate %s ",String.valueOf(row)));
                }
                if (!board.canMove(row-1,col-1)) {
                    System.out.println(String.format("Cannot move to location X:%s Y:%s",String.valueOf(col),String.valueOf(row)));
                    continue;
                }
                board.movePlayerToLocation(currentPlayer,row-1,col-1);
                switchPlayers(currentPlayer,nextPlayer);
            }
            board.printWinner();
        } catch (Exception e ){
            System.out.println("Game exited...");
            System.out.println(e.getMessage());
        }
    }
    private void switchPlayers(Player currentPlayer, Player nextPlayer){
        String tempPlayerPiece = currentPlayer.getPlayerPiece();
        boolean tempIsUser = currentPlayer.isUser();
        currentPlayer.swapPlayer(nextPlayer.getPlayerPiece(),nextPlayer.isUser());
        nextPlayer.swapPlayer(tempPlayerPiece,tempIsUser);
    }

}
