package com.nemo.footballassociation.Contracts.Modules.DbModels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game gameToUpdate;
    @BeforeEach
    void setUp() {
        Date dateTime = new Date(System.currentTimeMillis());
        gameToUpdate = new Game(0, dateTime);
    }

    @Test
    void update() {
        Date dateTime = new Date(System.currentTimeMillis());
        Game game = new Game(0, dateTime);
        Team home = new Team();
        Team away = new Team();
        TeamByLeagueBySeason homeTeam = new TeamByLeagueBySeason();
        TeamByLeagueBySeason awayTeam = new TeamByLeagueBySeason();
        homeTeam.setTeam(home);
        awayTeam.setTeam(away);
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        assertNull(gameToUpdate.getAwayTeam());
        assertNull(gameToUpdate.getHomeTeam());
        gameToUpdate.update(game);
        assertEquals(game.getAwayTeam(),gameToUpdate.getAwayTeam());
        assertEquals(game.getHomeTeam(),gameToUpdate.getHomeTeam());
    }
}