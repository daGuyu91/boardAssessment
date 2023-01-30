package com.assessment.board.logic;

import com.assessment.board.model.Pit;
import com.assessment.board.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {

    //Declaring constant variables for the game.
    private static final int   PLAYING_PITS = 6;
    private static final int   TOTAL_PITS  =  2*(PLAYING_PITS+1);
    private static final int   STONES = 6;


    //I'm using array list to declare pits and players for the game
    List<Pit> pits = new ArrayList<Pit>(PLAYING_PITS);
    List<Player> players = new ArrayList<Player>(2);

    public Board()  {

        //i initialise the game by creating 6 pits and add 6 stines to each pit when game starts
            pits.stream().forEach(pit ->{
                 pit = new Pit();
                 pit.addStones(STONES);
            });
    }

    //This method is for removing stones from a pit
    public  void  emptyStones()  {

        players.stream().forEach(player -> {
            pits.stream().forEach(pit -> {
                int pitIndex = pits.indexOf(pit);
                int stones = pit.removeStones();
                pits.get(getPitNum(player.getPlayerNum(),pitIndex)).addStones(stones);
            });
        });

    }

    //This method will determine when the game is over when the stones inthe pit is no more
    public boolean gameOver()  {
        AtomicBoolean isEndGame = new AtomicBoolean(false);
        players.forEach(player ->{
            AtomicInteger stones = new AtomicInteger();
            pits.stream().forEach(pit -> {
                int pitIndex = pits.indexOf(pit);
                stones.addAndGet(pits.get(getPitNum(player.getPlayerNum(), pitIndex)).getStones());
                if  (stones.equals("0")) {
                    isEndGame.set(true);
                }
            });
        });

        return  isEndGame;
    }

    public int  getGameBoard(int  playerNum)  {
        return  playerNum  *  (PLAYING_PITS+1);
    }

    public  int  getPitNum(int  playerNum, int  pitIndex) {
        return  playerNum  *  (PLAYING_PITS+1)  +  pitIndex;
    }

    private  boolean  isGameOver(int  pitIndex)  {
        return  pitIndex  %  (PLAYING_PITS+1)  ==  0;
    }

    public  boolean  move(int  currentPlayerNum,  int  chosenPitNum)  {
        int  pitIndex  =  getPitNum(currentPlayerNum, chosenPitNum);
        int  stones  =  pits.get(pitIndex).removeStones();
        while  (stones  !=  0)  {
            pitIndex--;
            if  (pitIndex  <  0)
                pitIndex  =  TOTAL_PITS  -  1;
            if  (pitIndex  !=  getGameBoard(otherPlayerIndex(currentPlayerNum)))  {
                pits.get(pitIndex).addStones(1);
                stones--;
            }
        }
        if  (pitIndex  ==  getGameBoard(currentPlayerNum))
            return  true;
        if  (pitOwner(pitIndex)  ==  currentPlayerNum  && pits.get(pitIndex).getStones()  ==  1)  {
            stones  =  pits.get(oppositePitIndex(pitIndex)).removeStones();
            pits.get(getGameBoard(currentPlayerNum)).addStones(stones);
        }
        return false;
    }

    public  int  oppositePitIndex(int  pitIndex)  {
        return  TOTAL_PITS  -  pitIndex;
    }

    public  int  otherPlayerIndex(int  playerNum)  {
        if  (playerNum  ==  0)
            return  1;
        else
            return  0;
    }

    public  int  pitOwner(int  pitIndex)  {
        return  pitIndex  /  (PLAYING_PITS+1);
    }

    public  void  setUpForPlay()  {

        pits.stream().forEach(pit -> {
            int pitIndex = pits.indexOf(pit);
            if(!isGameOver(pitIndex)){
                pits.get(pitIndex).addStones(STONES);
            }
        });
    }

    public  int  stonesInPit(int  playerNum)  {
        return  pits.get(getGameBoard(playerNum)).getStones();
    }

}
