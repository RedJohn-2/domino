package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Domino> dominoesOfPlayer;
    private String name;

    public Player(String name) {
        this.name = name;
        dominoesOfPlayer = new ArrayList<>();
    }

    public List<Domino> getDominoesOfPlayer() {
        return dominoesOfPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
