package model;

import java.util.List;

public class Domino {
    private final List<DominoValue> values;

    public Domino(List<DominoValue> values) {
        this.values = values;
    }

    public List<DominoValue> getValues() {
        return values;
    }
}
