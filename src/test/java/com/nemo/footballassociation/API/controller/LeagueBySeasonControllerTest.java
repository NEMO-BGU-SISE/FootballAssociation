package com.nemo.footballassociation.API.controller;

import com.nemo.footballassociation.Contracts.Modules.DbModels.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

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
            Team team11 = new Team();
            Team team22 = new Team();
            Team team33 = new Team();
            team1.setTeam(team11);
            team2.setTeam(team22);
            team3.setTeam(team33);
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

    }

    @Test
    void assigningGamesPolicy2() {
        leagueBySeason2.setGames(games6);
    }

    @Test
    void create() {
    }
}