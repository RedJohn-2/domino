package model;

import java.util.ArrayList;
import java.util.List;

public class GameArea {
    private List<Domino> dominoesOnGameArea;
    private List<Domino> dominoesInBazaar;
    private List<DominoValue> ends;

    public GameArea() {
        dominoesOnGameArea = new ArrayList<>();
        ends = new ArrayList<>();
    }

    public List<Domino> getDominoesOnGameArea() {
        return dominoesOnGameArea;
    }

    public List<DominoValue> getEnds() {
        return ends;
    }

    public List<Domino> getDominoesInBazaar() {
        return dominoesInBazaar;
    }

    public void setDominoesInBazaar(List<Domino> dominoesInBazaar) {
        this.dominoesInBazaar = dominoesInBazaar;
    }
}
