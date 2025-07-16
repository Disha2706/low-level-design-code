package org.bookingManagement.repository;

import org.bookingManagement.enums.Genre;
import org.bookingManagement.exceptions.LiveShowAlreadyPresentException;
import org.bookingManagement.exceptions.NoGenrePresentException;
import org.bookingManagement.model.LiveShow;
import org.bookingManagement.strategy.RankingStrategy;
import org.bookingManagement.strategy.StartTimeRankingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveShowRepository {
    private static final Map<String, LiveShow> registeredLiveShows = new HashMap<>();
    private static final Map<Genre, List<LiveShow>> showsByGenre = new HashMap<>();
    private RankingStrategy startTimeRankingStrategy = new StartTimeRankingStrategy();

    public void registerLiveShow(LiveShow liveShow){
        if(registeredLiveShows.containsKey(liveShow.getName()))
            throw new LiveShowAlreadyPresentException("Live show already exists int the system!");
        registeredLiveShows.put(liveShow.getName(), liveShow);

        if(!showsByGenre.containsKey(liveShow.getGenre())){
            showsByGenre.put(liveShow.getGenre(), List.of(liveShow));
        } else {
            showsByGenre.get(liveShow.getGenre()).add(liveShow);
        }
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
