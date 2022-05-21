package com.nemo.footballassociation.Contracts.Modules.DbModels;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class LeagueBySeason {
    @Id
    private int id;
    @ManyToOne
//    @JoinColumn(name="season_id")
    private Season season;
    @ManyToOne
//    @JoinColumn(name="league_id")
    private League league;
    @OneToMany
    private List<Game> games;
    @OneToMany
    private List<TeamByLeagueBySeason> teams;
    @ManyToOne
    private AssigningPolicy assigning_policy;

    private boolean isAssigned = false;

    public LeagueBySeason() {
    }

    public void update(LeagueBySeason other) {
        this.setSeason(other.getSeason());
        this.setLeague(other.getLeague());
        this.setGames(other.getGames());
        this.setTeams(other.getTeams());
        this.setAssigning_policy(other.getAssigning_policy());
        this.setAssigned(other.isAssigned());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TeamByLeagueBySeason> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamByLeagueBySeason> teams) {
        this.teams = teams;
    }

    public AssigningPolicy getAssigning_policy() {
        return assigning_policy;
    }

    public void setAssigning_policy(AssigningPolicy assigningPolicy) {
        this.assigning_policy = assigningPolicy;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public boolean AssigningGames() {
        if (this.assigning_policy == null || this.isAssigned || this.teams.size() < 2 || this.season == null || this.league == null) {
            return false;
        }
        if (this.assigning_policy.getNumOfMatchesBetweenTeamsInSeason() == 1) {
            int numOfGames1 = (this.factorial(this.teams.size()) / (this.factorial(this.teams.size() - 2) * (this.factorial(2))));
            if (numOfGames1 != this.games.size()) {
                return false;
            }
            Game curGame;
            int index = 0;
            for (TeamByLeagueBySeason teamHome : this.teams) {
                for (TeamByLeagueBySeason teamAway : this.teams) {
                    if (teamHome == teamAway) {
                        continue;
                    }
                    curGame = this.games.get(index++);
                    curGame.setAway_team(teamAway);
                    curGame.setHome_team(teamHome);
                }
            }
        } else if (this.assigning_policy.getNumOfMatchesBetweenTeamsInSeason() == 2) {
            int numOfGames2 = (this.factorial(this.teams.size()) / (this.factorial(this.teams.size() - 2)));
            if (numOfGames2 != this.games.size()) {
                return false;
            }
            Game curGame;
            int index = 0;
            for (TeamByLeagueBySeason teamHome : this.teams) {
                for (TeamByLeagueBySeason teamAway : this.teams) {
                    if (teamHome == teamAway) {
                        continue;
                    }
                    curGame = this.games.get(index++);
                    curGame.setAway_team(teamAway);
                    curGame.setHome_team(teamHome);
                    curGame = this.games.get(index++);
                    curGame.setAway_team(teamHome);
                    curGame.setHome_team(teamAway);
                }
            }
        }
        Date start = null;
        Date end = null;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd").parse("2022-1-1");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-1-1");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Date> dates = this.getDateRange(start, end);
        int numOfGames = this.games.size() - 1;
        Random rand = new Random();
        Date curDate;
        for (Game game : this.games) {
            int randomNumber = rand.nextInt(numOfGames--);
            curDate = dates.get(randomNumber);
            game.setDate_time(curDate);
            dates.remove(randomNumber);
        }
        return true;
    }

    private int factorial(int n) {
        if (n == 0)
            return 1;
        else {
            int fact = 1;
            for (int i = 1; i <= n; i++) {
                fact = fact * i;
            }
            return fact;
        }
    }

    public List<Date> getDateRange(Date start, Date end) {
        if (start == null || end == null || end.before(start)) {
            return null;
        }
        List<Date> ret = new ArrayList<Date>();
        Date curDate = start;
        Calendar cal = Calendar.getInstance();
        while (curDate.before(end) || curDate.equals(end)) {
            ret.add(curDate);
            cal.setTime(curDate);
            cal.add(Calendar.DATE, 7);
            curDate = cal.getTime();
        }
        return ret;
    }
}
