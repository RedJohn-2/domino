package service;

import model.Domino;
import model.DominoValue;
import model.GameArea;
import model.Player;

import java.util.ArrayList;
import java.util.Arrays;


public class PlayerService {

    public void addDomino(Player player, Domino domino, DominoService ds) {
        if (player.getDominoesOfPlayer().isEmpty()) {
            player.getDominoesOfPlayer().add(domino);
            return;
        }

        for (int i = 0; i < player.getDominoesOfPlayer().size(); i++) {
            if (ds.getSumValues(domino) > ds.getSumValues(player.getDominoesOfPlayer().get(i))) {
                player.getDominoesOfPlayer().add(i, domino);
                return;
            }
        }

        player.getDominoesOfPlayer().add(domino);
    }

    public int getSumDomino(Player player, DominoService ds) {
        int sum = 0;
        for (Domino d : player.getDominoesOfPlayer()) {
            sum += ds.getSumValues(d);
        }
        return sum;
    }

}
