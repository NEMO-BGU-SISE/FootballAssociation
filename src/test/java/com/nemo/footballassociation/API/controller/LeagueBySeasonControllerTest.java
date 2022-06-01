package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Modules.DbModels.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeagueBySeasonControllerTest {
    private URL url;
    private URL urlLogin;
    private HttpURLConnection conn;
    private HttpURLConnection connLogin;
    private APIController apiController;
    private APIController apiControllerLogin;
    private League league;
    private Season season;
    private LeagueBySeason leagueBySeason1;
    private LeagueBySeason leagueBySeason2;
    private Game game1;
    private Game game2;
    private Game game3;
    private Game game4;
    private Game game5;
    private Game game6;
    private TeamByLeagueBySeason team1;
    private TeamByLeagueBySeason team2;
    private TeamByLeagueBySeason team3;
    private List<Game> games6;
    private List<Game> games3;

    @BeforeEach
    void setUp() {
        try {
            url = new URL("http://localhost:8080/referee");
            apiController = new APIController(url);
            conn = apiController.getConn();
            urlLogin = new URL("http://localhost:8080/login");
            apiControllerLogin = new APIController(urlLogin);
            connLogin = apiControllerLogin.getConn();

            games3 = new ArrayList<>();
            games6 = new ArrayList<>();

            Team team11 = new Team();
            Team team22 = new Team();
            Team team33 = new Team();

            team1 = new TeamByLeagueBySeason();
            team2 = new TeamByLeagueBySeason();
            team3 = new TeamByLeagueBySeason();

            team1.setTeam(team11);
            team2.setTeam(team22);
            team3.setTeam(team33);
            team1.setId(1);
            team2.setId(2);
            team3.setId(3);

            game1 = new Game();
            game2 = new Game();
            game3 = new Game();
            game4 = new Game();
            game5 = new Game();
            game6 = new Game();

            games6.add(game1);
            games6.add(game2);
            games6.add(game3);
            games6.add(game4);
            games6.add(game5);
            games6.add(game6);

            games3.add(game1);
            games3.add(game2);
            games3.add(game3);

            season = new Season();
            league = new League();
            leagueBySeason1 = new LeagueBySeason(season, league, 1);
            leagueBySeason2 = new LeagueBySeason(season, league, 2);

            leagueBySeason1.getTeams().add(team1);
            leagueBySeason1.getTeams().add(team2);
            leagueBySeason1.getTeams().add(team3);

            leagueBySeason2.getTeams().add(team1);
            leagueBySeason2.getTeams().add(team2);
            leagueBySeason2.getTeams().add(team3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if(conn.getResponseCode() == 200) {
                conn.disconnect();
            }
            if(connLogin.getResponseCode() == 200) {
                connLogin.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAll() {

    }

    @Test
    void assigningGamesPolicy1() {
        leagueBySeason1.setGames(games3);
        team1.setLeagueBySeason(leagueBySeason1);
        team2.setLeagueBySeason(leagueBySeason1);
        team3.setLeagueBySeason(leagueBySeason1);
        for (Game curGame: leagueBySeason1.getGames()) {
            assertNull(curGame.getAwayTeam());
        }
        leagueBySeason1.AssigningGames();
        ArrayList<int[]> valid = new ArrayList<>();
        ArrayList<int[]> notValid = new ArrayList<>();
        ArrayList<String> validString = new ArrayList<>();
        ArrayList<String> notValidString = new ArrayList<>();
        int[] teams;
        String teamsString;
        int[] notTeams;
        String notTeamsString;
        for (Game curGame: leagueBySeason1.getGames()) {
            assertNotNull(curGame.getAwayTeam());
            teams = new int[]{curGame.getHomeTeam().getId(), curGame.getAwayTeam().getId()};
            notTeams = new int[]{curGame.getAwayTeam().getId(), curGame.getHomeTeam().getId()};
            teamsString = "{"+ curGame.getHomeTeam().getId()+ "," + curGame.getAwayTeam().getId() + "}";
            notTeamsString = "{"+ curGame.getAwayTeam().getId()+ "," + curGame.getHomeTeam().getId() + "}";
            assertFalse(notValidString.contains(teamsString));
            valid.add(teams);
            notValid.add(notTeams);
            validString.add(teamsString);
            notValidString.add(notTeamsString);
        }
        assertEquals(games3.size(), valid.size());
        assertEquals(games3.size(), notValid.size());
        String oppositeTeamsString;
        for (int[] curTeams: valid) {
            oppositeTeamsString =  "{" + curTeams[1]+","+ curTeams[0]+"}";
            assertTrue(notValidString.contains(oppositeTeamsString));
        }
    }

    @Test
    void assigningGamesPolicy2() {
        leagueBySeason2.setGames(games6);
        team1.setLeagueBySeason(leagueBySeason2);
        team2.setLeagueBySeason(leagueBySeason2);
        team3.setLeagueBySeason(leagueBySeason2);
        for (Game curGame: leagueBySeason2.getGames()) {
            assertNull(curGame.getAwayTeam());
        }
        leagueBySeason2.AssigningGames();
        ArrayList<int[]> allGames = new ArrayList<>();
        ArrayList<String> allGamesString = new ArrayList<>();
        int[] teams;
        String teamsString;
        for (Game curGame: leagueBySeason2.getGames()) {
            assertNotNull(curGame.getAwayTeam());
            teams = new int[]{curGame.getHomeTeam().getId(), curGame.getAwayTeam().getId()};
            teamsString = "{"+ curGame.getHomeTeam().getId()+ "," + curGame.getAwayTeam().getId() + "}";
            allGames.add(teams);
            allGamesString.add(teamsString);
        }
        assertEquals(games6.size(), allGames.size());
        String oppositeTeamsString;
        for (int[] curTeams: allGames) {
            oppositeTeamsString = "{" + curTeams[1]+"," + curTeams[0]+ "}";
            assertTrue(allGamesString.contains(oppositeTeamsString));
        }
    }

    @Test
    void create() {
    }
}