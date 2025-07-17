package org.bookingManagement.repository;

import org.bookingManagement.enums.Genre;
import org.bookingManagement.exceptions.LiveShowAlreadyPresentException;
import org.bookingManagement.exceptions.NoGenrePresentException;
import org.bookingManagement.model.LiveShow;
import org.bookingManagement.strategy.RankingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bookingManagement.strategy.RankingStrategyFactory.getRankingStrategy;

public enum LiveShowRepository {
    INSTANCE;
    private static final Map<String, LiveShow> registeredLiveShows = new HashMap<>();
    private static final Map<Genre, List<LiveShow>> showsByGenre = new HashMap<>();
    private final RankingStrategy startTimeRankingStrategy = getRankingStrategy("startTime");

    public void registerLiveShow(LiveShow liveShow){
        if(registeredLiveShows.containsKey(liveShow.getName()))
            throw new LiveShowAlreadyPresentException("Live show already exists int the system!");
        registeredLiveShows.put(liveShow.getName(), liveShow);

        showsByGenre.computeIfAbsent(liveShow.getGenre(), k -> new ArrayList<>()).add(liveShow);
    }

    public Boolean isLiveShowRegistered(String showName){
        return registeredLiveShows.containsKey(showName);
    }

    public List<LiveShow> getLiveShowsByStartTime(Genre genre){
        List<LiveShow> liveShowsByGenre = getLiveShowByGenre(genre);
        return startTimeRankingStrategy.rank(liveShowsByGenre);
    }

    private List<LiveShow> getLiveShowByGenre(Genre genre){
        if(!showsByGenre.containsKey(genre))
            throw  new NoGenrePresentException("No Genre Present in the system!");
        return showsByGenre.get(genre);
    }

    public boolean addSlotToShow(String showName, int hour, int capacity) {
        LiveShow show = registeredLiveShows.get(showName);
        return show.addSlot(hour, capacity);
    }

    public LiveShow getLiveShow(String name){
        return registeredLiveShows.get(name);
    }

}
