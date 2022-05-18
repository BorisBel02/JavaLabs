package com.viruswarmultiplayer.GameModel;

import com.viruswarmultiplayer.Exception.GameException;
import com.viruswarmultiplayer.Observer.Observable;
import com.viruswarmultiplayer.Observer.Observer;

import javafx.util.Pair;
import java.util.HashMap;
import java.util.LinkedList;


public class Game implements Observable {
    LinkedList<Observer> observersList = new LinkedList<>();
    Character[][] field = new Character[10][10];
    Integer player1Score;
    Integer player2Score;

    char playerTurn = 'X';
    char enemy = 'O';

    Integer turnCounter = 0;
    Integer filledCells = 0;

    HashMap<Pair<Integer, Integer>, Boolean> chain = new HashMap<>();
    Boolean find(Integer x, Integer y, char what){
        for(Integer i = -1; i <= 1; ++i){
            for(Integer j = -1; j <= 1; ++j){
                if((x + i >= 0 && x + i < 10 && y + j >= 0 && y + j < 10) && field[x + i][y + j] == what){
                    return true;
                }
            }
        }
        return false;
    }

    Boolean accessible(Integer x, Integer y){
        if(find(x, y, playerTurn)){
            return true;
        }
        for(Integer i = -1; i <= 1; ++i){
            for(Integer j = -1; j <= 1; ++j){
                int coord1 = x + i;
                int coord2 = y + j;
                if((coord1 >= 0 && coord1 < 10 && coord2 >= 0 && coord2 < 10) && field[x + i][y + j] == Character.toLowerCase(playerTurn) && (coord1 != x && coord2 != y)){
                    if(accessible(x + i, y + j)){
                        return true;
                    }

                }
            }
        }
        return false;
    }

    
    public Game(){
        for(var i : field){
            for(int j = 0; j < 10; ++j){
                i[j] = '*';
            }

        }
        field[0][0] = 'X';
        field[9][9] = 'O';
        player1Score = 1;
        player2Score = 1;
    }
    public void killEnemy(Integer x, Integer y){
        if(accessible(x, y) && field[x][y] == enemy){
            field[x][y] = Character.toLowerCase(playerTurn);

        }
        else{
            throw new GameException("You can't kill enemy virus in this cell");
        }
        if(playerTurn == 'X'){
            --player2Score;
        }
        else{
            --player1Score;
        }
        ++turnCounter;
        if(turnCounter%3 == 0){
            char tmp = playerTurn;
            playerTurn = enemy;
            enemy = tmp;
        }
        notifySubscribers(x, y, Character.toLowerCase(enemy));
    }
    public void grow(Integer x, Integer y){
        if (field[x][y] == '*' && accessible(x, y)) {
            field[x][y] = playerTurn;
            ++turnCounter;
        }
        else{
            throw new GameException("You can't set your virus in this cell");
        }

        if(playerTurn == 'X'){
            ++player1Score;
        }
        else{
            ++player2Score;
        }
        ++filledCells;
        if(turnCounter%3 == 0){
            char tmp = playerTurn;
            playerTurn = enemy;
            enemy = tmp;
        }
        notifySubscribers(x, y, playerTurn);
    }

    @Override
    public void reg(Observer o) {
        observersList.add(o);
    }

    public void notifySubscribers(Integer x, Integer y, Character cell){
        for(var subscriber : observersList){
            subscriber.update(x, y, cell);
        }
    }
    public Character[][] getField(){
        return field.clone();
    }
    public Pair<Integer, Integer> getScore(){
        return new Pair<>(player1Score, player2Score);
    }
    public char getTurn(){
        return playerTurn;
    }
    public Integer getTurnQty(){
        return turnCounter;
    }


}
