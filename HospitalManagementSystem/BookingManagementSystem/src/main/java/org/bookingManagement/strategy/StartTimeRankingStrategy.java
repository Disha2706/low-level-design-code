package org.bookingManagement.strategy;

import lombok.Data;
import org.bookingManagement.model.LiveShow;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StartTimeRankingStrategy implements RankingStrategy{

    @Override
    public List<LiveShow> rank(List<LiveShow> shows) {
        return shows.stream()
                .sorted(Comparator.comparingInt(show -> show.getSlotMap().keySet().stream().min(Integer::compareTo).orElse(24)))
                .collect(Collectors.toList());
    }
}
