package com.nemo.footballassociation.API.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
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
    }

    @Test
    void subscriptionLogin() {
        try {
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth = apiControllerLogin.getResponse();
            int status = connLogin.getResponseCode();
            assertEquals(200, status);
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            auth = apiControllerLogin.getResponse();
            status = connLogin.getResponseCode();
            assertEquals(400, status);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void subscriptionLogout() {
    }
}