package com.beditsch.ing.onlinegame.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculateRequest {
    private final int groupCount;
    private final ArrayList<Clan> clans;

    public CalculateRequest(int groupCount, ArrayList<Clan> clans) {
        this.groupCount = groupCount;
        this.clans = clans;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public ArrayList<Clan> getClans() {
        return clans;
    }
}
