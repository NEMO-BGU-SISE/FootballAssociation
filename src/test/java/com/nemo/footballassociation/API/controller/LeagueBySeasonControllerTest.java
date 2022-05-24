package com.nemo.footballassociation.API.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LeagueBySeasonControllerTest {
    private URL url;
    private URL urlLogin;
    private HttpURLConnection conn;
    private HttpURLConnection connLogin;
    private APIController apiController;
    private APIController apiControllerLogin;

    @BeforeEach
    void setUp() {
        try {
            url = new URL("http://localhost:8080/referee");
            apiController = new APIController(url);
            conn = apiController.getConn();
            urlLogin = new URL("http://localhost:8080/login");
            apiControllerLogin = new APIController(urlLogin);
            connLogin = apiControllerLogin.getConn();
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
    void assigningGames() {
    }

    @Test
    void create() {
    }
}