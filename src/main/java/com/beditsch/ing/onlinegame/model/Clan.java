package com.beditsch.ing.onlinegame.model;

import org.jetbrains.annotations.NotNull;

public class Clan implements Comparable<Clan> {
    private final int numberOfPlayers;
    private final int points;

    public Clan(int numberOfPlayers, int points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(@NotNull Clan o) {
        if (this.getPoints() != o.getPoints()) {
            return Integer.compare(this.getPoints(), o.getPoints());
        } else {
            return (-1) * Integer.compare(this.getNumberOfPlayers(), o.getNumberOfPlayers());
        }
    }
}
