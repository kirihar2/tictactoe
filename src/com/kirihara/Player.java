package com.kirihara;

public class Player {
    private String playerPiece;
    private boolean isUser;
    public Player(){
        this.playerPiece = "";
        this.isUser=false;
    }

    public Player(String piece,boolean isUser){
        this.playerPiece = piece;
        this.isUser = isUser;
    }

    public void swapPlayer(String playerPiece, boolean isUser){
        setPlayerPiece(playerPiece);
        setIsUser(isUser);
    }

    public boolean isUser(){
        return isUser;
    }

    public String getPlayerPiece(){return this.playerPiece;}

    public void setPlayerPiece(String playerPiece){this.playerPiece = playerPiece;}
    public void setIsUser(boolean isUser){this.isUser = isUser;}
}
