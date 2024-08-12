package org.example;

import java.util.List;

public class Innings {
    private String team;
    private List<Overs> overs;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Overs> getOvers() {
        return overs;
    }

    public void setOvers(List<Overs> overs) {
        this.overs = overs;
    }
}

