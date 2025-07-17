package org.bookingManagement.strategy;

public class RankingStrategyFactory {
    private static RankingStrategy startTimeRankingStrategy;

    public static RankingStrategy getRankingStrategy(String strategyType){
        switch(strategyType){
            case "startTime": return new StartTimeRankingStrategy();
            default: return startTimeRankingStrategy;
        }
    }
}
