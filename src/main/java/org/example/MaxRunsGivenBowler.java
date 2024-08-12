package org.example;

import java.util.HashMap;
import java.util.Map;

public class MaxRunsGivenBowler implements PlayerStats {
    private final Map<String, Map<String, Integer>> teamBowlerRuns = new HashMap<>();

    @Override
    public String getMaxScoringBatsman(String team) {
        return null;
    }

    @Override
    public String getMinScoringBatsman(String team) {
        return null;
    }

    @Override
    public String getMaxRunsGivenBowler(String team) {
        return teamBowlerRuns.get(team).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " runs)")
                .orElse("No valid data");
    }

    @Override
    public String getMinRunsGivenBowler(String team) {
        return teamBowlerRuns.get(team).entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey() + " (" + entry.getValue() + " runs)")
                .orElse("No valid data");
    }

    @Override
    public void calculateStats(Cricket cricket) {
        for (Innings innings : cricket.getInnings()) {
            String team = innings.getTeam();
            teamBowlerRuns.putIfAbsent(team, new HashMap<>());

            for (Overs over : innings.getOvers()) {
                for (Deliveries delivery : over.getDeliveries()) {
                    try {
                        int runs = parseRuns(delivery.getRuns().getTotal());
                        if (runs > 6) {
                            throw new CustomException("Runs scored in a ball cannot be greater than 6: " + runs);
                        }
                        teamBowlerRuns.get(team).merge(delivery.getBowler(), runs, Integer::sum);
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
