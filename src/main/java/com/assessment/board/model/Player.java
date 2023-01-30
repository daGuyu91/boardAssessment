package com.assessment.board.model;

public class Player {
    private String  name;

    private int  playerNum;

    public Player(String name, int playerNum)  {
        this.name  =  name;
        this.playerNum  =  playerNum;
    }

    public String getName(){
        return name;
    }

    public  int  getPlayerNum()  {
        return  this.playerNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

}
