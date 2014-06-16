package com.katfang.test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: greg
 * Date: 6/21/13
 * Time: 2:38 PM
 */
public class Game {

    private String creator;
    private String next;
    private List<Turn> turns;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Game() { }

    Game(String creator) {
        this.creator = creator;
        turns = new ArrayList<Turn>();
        this.next = "picture";
    }

    public String getCreator() {
        return creator;
    }

    public String getNext() { return next; }

    public List<Turn> getTurns() { return turns; }

    public void addTurn(Turn t) {
        turns.add(t);
    }
}