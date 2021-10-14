package service;

import model.Domino;
import model.DominoValue;

public class DominoService {
    public boolean isDouble(Domino domino) {
        return domino.getValues().get(0).equals(domino.getValues().get(1));
    }

    public Integer getSumValues(Domino domino) {
        Integer sum = 0;

        for (DominoValue value : domino.getValues()) {
            sum += value.ordinal();
        }

        return sum;
    }
}
