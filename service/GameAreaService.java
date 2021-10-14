package service;

import model.Domino;
import model.DominoValue;
import model.GameArea;

import java.util.List;

public class GameAreaService {

    public void addDominoOnGameArea(GameArea gameArea, Domino domino, DominoValue suitableEnd) {
        if (gameArea.getDominoesOnGameArea().isEmpty()) {
            gameArea.getDominoesOnGameArea().add(domino);
            for (DominoValue value : domino.getValues()) {
                gameArea.getEnds().add(value);
            }
            return;
        }

        DominoValue newEnd = domino.getValues().get(0).equals(suitableEnd) ? domino.getValues().get(1) : domino.getValues().get(0);

        if (suitableEnd.equals(gameArea.getEnds().get(0))) {
            gameArea.getDominoesOnGameArea().add(0, domino);
            gameArea.getEnds().set(0, newEnd);
        } else {
            gameArea.getDominoesOnGameArea().add(domino);
            gameArea.getEnds().set(1, newEnd);
        }

    }

    public DominoValue findSuitableEnd(GameArea gameArea, Domino domino) {
        for (DominoValue v1 : gameArea.getEnds()) {
            for (DominoValue v2 : domino.getValues()) {
                if (v1.equals(v2)) {
                    return v1;
                }
            }
        }

        return null;
    }

    public int getRandomIndexFromBazaar(GameArea gameArea) {
        return (int)(Math.random() * gameArea.getDominoesInBazaar().size());
    }

    public boolean isFish(GameArea gameArea) {
        int countFirstValue = 0;
        int countSecondValue = 0;
        for (Domino d : gameArea.getDominoesOnGameArea()) {
            for (DominoValue v : d.getValues()) {
                if (gameArea.getEnds().get(0).equals(v)) {
                    countFirstValue++;
                }

                if (gameArea.getEnds().get(1).equals(v)) {
                    countSecondValue++;
                }
            }
        }

        return countFirstValue > DominoValue.values().length && countSecondValue > DominoValue.values().length;
    }

}
