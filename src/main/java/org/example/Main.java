package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File inputFile = new File("src/main/resources/Cricket_V2.json");

        try {
            Cricket cricket = objectMapper.readValue(inputFile, Cricket.class);

            PlayerStats maxScoringBatsman = new MaxScoringBatsman();
            PlayerStats maxRunsGivenBowler = new MaxRunsGivenBowler();
//            maxScoringBatsman.
//            maxScoringBatsman.getMaxRunsGivenBowler()

            maxScoringBatsman.calculateStats(cricket);
            maxRunsGivenBowler.calculateStats(cricket);


            Set<String> uniqueTeams = new HashSet<>();

            for (Innings inning : cricket.getInnings()) {
                uniqueTeams.add(inning.getTeam());
            }

            for (String team : uniqueTeams) {
                System.out.println("Team: " + team);
                System.out.println("Max Scoring Batsman: " + maxScoringBatsman.getMaxScoringBatsman(team));
                System.out.println("Min Scoring Batsman: " + maxScoringBatsman.getMinScoringBatsman(team));
                System.out.println("Max Runs Given Bowler: " + maxRunsGivenBowler.getMaxRunsGivenBowler(team));
                System.out.println("Min Runs Given Bowler: " + maxRunsGivenBowler.getMinRunsGivenBowler(team));
                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
