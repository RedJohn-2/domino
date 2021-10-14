package model;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Game {

    private GameArea gameArea;
    private Queue<Player> players;
    private boolean gameOver;
    private String winner;

    public Game(int firstPlayer) {
        gameOver = false;
        gameArea = new GameArea();
    }

    public GameArea getGameArea() {
        return gameArea;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
