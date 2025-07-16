package org.bookingManagement.strategy;

import org.bookingManagement.model.LiveShow;

import java.util.List;

public interface RankingStrategy {
    public List<LiveShow> rank(List<LiveShow> shows);
}
