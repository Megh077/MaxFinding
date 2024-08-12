package org.example;

public interface PlayerStats {
    void calculateStats(Cricket cricket);
    String getMaxScoringBatsman(String team);
    String getMinScoringBatsman(String team);
    String getMaxRunsGivenBowler(String team);
    String getMinRunsGivenBowler(String team);
}


