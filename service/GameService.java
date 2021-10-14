package service;

import model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameService {

    public static final int COUNT_PLAYERS = 4;
    public static final int COUNT_DOMINOES = 5;

    public void startGame(Game game, GameAreaService gameAreaService, PlayerService ps, DominoService ds) {
        createBazaar(game);
        createPlayers(game, ds, gameAreaService, ps);
        while (!game.isGameOver()) {
            printStartMove(game);
            move(game, ps, ds, gameAreaService);
            printEndMove();
        }
    }

    private void createBazaar(Game game) {
        List<Domino> dominoes = new ArrayList<>();
        for (int i = 0; i < DominoValue.values().length; i++) {
            for (int j = i; j < DominoValue.values().length; j ++ ) {
                List<DominoValue> domino = new ArrayList<>();
                domino.add(DominoValue.values()[i]);
                domino.add(DominoValue.values()[j]);
                dominoes.add(new Domino(domino));
            }
        }
        game.getGameArea().setDominoesInBazaar(dominoes);
    }

    private void createPlayers(Game game, DominoService ds, GameAreaService gameAreaService, PlayerService ps) {
        Queue<Player> players = new LinkedList<>();
        for (int i = 0; i < COUNT_PLAYERS; i++) {
            Player player = new Player("Игрок " + i);
            for (int j = 0; j < COUNT_DOMINOES; j++) {
                int index = gameAreaService.getRandomIndexFromBazaar(game.getGameArea());
                ps.addDomino(player, game.getGameArea().getDominoesInBazaar().get(index), ds);
                game.getGameArea().getDominoesInBazaar().remove(index);
            }
            players.add(player);
        }
        game.setPlayers(players);
    }

    private void move(Game game, PlayerService ps, DominoService ds, GameAreaService gameAreaService) {
        movePlayer(game, game.getGameArea(), game.getPlayers().peek(), ps, ds, gameAreaService);
        game.getPlayers().add(game.getPlayers().poll());

    }

    private void movePlayer(Game game, GameArea gameArea, Player player, PlayerService ps, DominoService ds, GameAreaService gameAreaService) {
        boolean moveIsMade = false;
        for (int i = 0; i < player.getDominoesOfPlayer().size(); i++) {
            DominoValue suitableEnd = gameAreaService.findSuitableEnd(gameArea, player.getDominoesOfPlayer().get(i));
            if (gameArea.getDominoesOnGameArea().isEmpty() || (suitableEnd != null)) {
                gameAreaService.addDominoOnGameArea(gameArea, player.getDominoesOfPlayer().get(i), suitableEnd);
                player.getDominoesOfPlayer().remove(i);
                moveIsMade = true;
                break;
            }
        }

        if (!moveIsMade) {
            takeDominoFromBazaar(ps, ds, gameAreaService, player, gameArea);
        }

        if ((player.getDominoesOfPlayer().size() == 0) || (gameAreaService.isFish(game.getGameArea()))) {
            gameOver(game, ps, ds);
        }
    }

    private void takeDominoFromBazaar(PlayerService ps, DominoService ds, GameAreaService gameAreaService, Player player, GameArea gameArea) {
        while (!gameArea.getDominoesInBazaar().isEmpty()) {
            int index = gameAreaService.getRandomIndexFromBazaar(gameArea);
            Domino takenDomino = gameArea.getDominoesInBazaar().get(index);
            ps.addDomino(player, takenDomino, ds);
            gameArea.getDominoesInBazaar().remove(index);
            DominoValue suitableEnd = gameAreaService.findSuitableEnd(gameArea, takenDomino);
            if (gameArea.getDominoesOnGameArea().isEmpty() || (suitableEnd != null)) {
                gameAreaService.addDominoOnGameArea(gameArea, takenDomino, suitableEnd);
                player.getDominoesOfPlayer().remove(takenDomino);
                return;
            }
        }
    }

    private void gameOver(Game game, PlayerService ps, DominoService ds) {
        Integer minValue = ps.getSumDomino(game.getPlayers().peek(), ds);
        Player playerWithMinValue = game.getPlayers().peek();
        for (Player p : game.getPlayers()) {
            if (ps.getSumDomino(p, ds) < minValue) {
                playerWithMinValue = p;
                minValue = ps.getSumDomino(p, ds);
            }
        }
        game.setWinner(playerWithMinValue.getName());
        game.setGameOver(true);
    }

    private void printStartMove(Game game) {
        System.out.println("----------------------------------------------");
        printPlayer(game.getPlayers().peek());
        printGameArea(game.getGameArea());
    }

    private void printEndMove(Game game) {
        printPlayer(game.getPlayers().peek());
        printGameArea(game.getGameArea());
        System.out.println("----------------------------------------------");
    }

    private void printPlayer(Player player) {
        System.out.print(player.getName() + ": ");
        for (Domino d : player.getDominoesOfPlayer()) {
            System.out.print("(");
            for (DominoValue v : d.getValues()) {
                System.out.print(v.ordinal() + " ");
            }
            System.out.print(")");
        }
    }

    private void printGameArea(GameArea gameArea) {
        System.out.print("Игровое поле : ");
        DominoValue lastEnd = gameArea.getEnds().get(0);
        for (Domino d : gameArea.getDominoesOnGameArea()) {
            System.out.print("(" + lastEnd + " ");
            lastEnd = d.getValues().get(0).equals(lastEnd) ? d.getValues().get(1) : d.getValues().get(0);
            System.out.print(lastEnd + ")");
        }
    }

}
