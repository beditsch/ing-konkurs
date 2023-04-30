package com.beditsch.ing.onlinegame;

import com.beditsch.ing.onlinegame.model.CalculateRequest;
import com.beditsch.ing.onlinegame.model.Clan;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("onlinegame")
public class OnlineGameController {
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "calculate"
    )
    public List<List<Clan>> calculate(@NotNull @RequestBody CalculateRequest request) {
        int maxGroupCount = request.getGroupCount();
        ArrayList<Clan> clans = request.getClans();
        sortClans(clans);

        ArrayList<List<Clan>> order = new ArrayList<>();
        ArrayList<Integer> spotsLeft = new ArrayList<>();
        addNextGameEntry(order, spotsLeft, maxGroupCount);
        int firstEntryWithFreeSpotsIndex = 0;

        ListIterator<Clan> clansIt = clans.listIterator(clans.size());

        while (clansIt.hasPrevious()) {
            Clan clan = clansIt.previous();
            int clanEntryIndex = getGameEntryIndexForClan(spotsLeft, firstEntryWithFreeSpotsIndex, clan);
            if (clanEntryIndex < 0) {
                addNextGameEntry(order, spotsLeft, maxGroupCount, clan);
            } else {
                order.get(clanEntryIndex).add(clan);
                spotsLeft.set(clanEntryIndex, spotsLeft.get(clanEntryIndex) - clan.getNumberOfPlayers());
                if (spotsLeft.get(firstEntryWithFreeSpotsIndex) == 0) {
                    int freeSpotsEntryIndex = getNextEntryWithFreeSpotsIndex(spotsLeft, firstEntryWithFreeSpotsIndex);
                    if (freeSpotsEntryIndex < 0) {
                        addNextGameEntry(order, spotsLeft, maxGroupCount);
                        firstEntryWithFreeSpotsIndex = spotsLeft.size() - 1;
                    } else {
                        firstEntryWithFreeSpotsIndex = freeSpotsEntryIndex;
                    }
                }
            }
        }

        if (order.get(order.size() - 1).isEmpty()) {
            order.remove(order.size() - 1);
        }
        return order;
    }

    private void addNextGameEntry(List<List<Clan>> order, List<Integer> remainingSpots, int maxGroupCount) {
        order.add(new ArrayList<>());
        remainingSpots.add(maxGroupCount);
    }

    private void addNextGameEntry(List<List<Clan>> order, List<Integer> remainingSpots, int maxGroupCount, Clan clan) {
        List<Clan> newEntry = new ArrayList<>();
        newEntry.add(clan);
        order.add(newEntry);
        remainingSpots.add(maxGroupCount - clan.getNumberOfPlayers());
    }

    private int getGameEntryIndexForClan(List<Integer> remainingSpots, int firstEntryWithSpotsIndex, Clan clan) {
        for (int i = firstEntryWithSpotsIndex; i < remainingSpots.size(); i++ ) {
            if (remainingSpots.get(i) >= clan.getNumberOfPlayers()) return i;
        }
        return -1;
    }

    private int getNextEntryWithFreeSpotsIndex(List<Integer> remainingSpots, int currentIndex) {
        for (int i = currentIndex; i < remainingSpots.size(); i++) {
            if (remainingSpots.get(i) > 0) return i;
        }
        return -1;
    }

    private void sortClans(List<Clan> clans) {
        clans.sort((Clan c1, Clan c2) -> {
            if (c1.getPoints() != c2.getPoints()) {
                return Integer.compare(c1.getPoints(), c2.getPoints());
            } else {
                return (-1) * Integer.compare(c1.getNumberOfPlayers(), c2.getNumberOfPlayers());
            }
        });
    }
}
