package org.example;

import java.util.HashMap;
import java.util.Map;

public class MaxScoringBatsman implements PlayerStats {
    private final Map<String, Map<String, Integer>> teamBatsmanScores = new HashMap<>();

    @Override
    public String getMaxScoringBatsman(String team) {
        return teamBatsmanScores.get(team).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " runs)")
                .orElse("No valid data");
    }

    @Override
    public String getMinScoringBatsman(String team) {
        return teamBatsmanScores.get(team).entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " runs)")
                .orElse("No valid data");
    }

    @Override
    public String getMaxRunsGivenBowler(String team) {
        return null;
    }

    @Override
    public String getMinRunsGivenBowler(String team) {
        return null;
    }

    @Override
    public void calculateStats(Cricket cricket) {
        for (Innings innings : cricket.getInnings()) {
            String team = innings.getTeam();
            teamBatsmanScores.putIfAbsent(team, new HashMap<>());

            for (Overs over : innings.getOvers()) {
                for (Deliveries delivery : over.getDeliveries()) {
                    try {
                        int runs = parseRuns(delivery.getRuns().getBatter());
                        if (runs > 6) {
                            throw new CustomException("Runs scored in a ball cannot be greater than 6: " + runs);
                        }
                        teamBatsmanScores.get(team).merge(delivery.getBatter(), runs, Integer::sum);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid run value, skipping delivery: " + e.getMessage());
                    } catch (CustomException e) {
                        System.out.println("Custom exception, skipping delivery: " + e.getMessage());
                    }
                }
            }
        }
    }

    private int parseRuns(String runs) throws NumberFormatException {
        return Integer.parseInt(runs);
    }
}
