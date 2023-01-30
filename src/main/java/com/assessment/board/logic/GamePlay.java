package com.assessment.board.logic;

import com.assessment.board.model.Player;

import java.util.List;

public class GamePlay {
    private int  currentPlayer  =  0;

    private Board  board;

    private List<Player>  players;

    public GamePlay(String  player1,  String  player2)  {

        try {
            board = new Board();
            board.setUpForPlay();
            players.add(new Player(player1, 1));
            players.add(new Player(player2, 2));
            currentPlayer = 0;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public List<Player> getPlayers() {
        return players;
    }

    public void play(int pitIndex,int currentPlayer) {

        try {
            boolean checkTurn = board.move(currentPlayer, pitIndex);

            if (!checkTurn)

                if (currentPlayer == 0) {
                    currentPlayer = 1;
                } else {
                    currentPlayer = 0;
                }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getWinner(){
        board.emptyStones();
        if  (board.stonesInPit(0)  > board.stonesInPit(1)){
            return players.get(0).getName();
        }
        else
        if (board.stonesInPit(0)  <  board.stonesInPit(1)){
            return players.get(1).getName();
        }
        else
            return "Draw";
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
