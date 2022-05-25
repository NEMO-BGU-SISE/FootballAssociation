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
    private URL urlLogout;
    private HttpURLConnection conn;
    private HttpURLConnection connLogin;
    private HttpURLConnection connLogout;
    private APIController apiController;
    private APIController apiControllerLogin;
    private APIController apiControllerLogout;

    @BeforeEach
    void setUp() {
        try {
            url = new URL("http://localhost:8080/referee");
            apiController = new APIController(url);
            conn = apiController.getConn();
            urlLogin = new URL("http://localhost:8080/login");
            apiControllerLogin = new APIController(urlLogin);
            connLogin = apiControllerLogin.getConn();
            apiControllerLogout = new APIController(urlLogin);
            connLogout = apiControllerLogout.getConn();
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
            if(connLogout.getResponseCode() == 200) {
                connLogout.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void subscriptionLoginGood() {
        try {
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth1 = apiControllerLogin.getResponse();
            int status = connLogin.getResponseCode();
            assertEquals(200, status);
            urlLogin = new URL("http://localhost:8080/login");
            apiControllerLogin = new APIController(urlLogin);
            connLogin = apiControllerLogin.getConn();
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth2 = apiControllerLogin.getResponse();
            status = connLogin.getResponseCode();
            assertEquals(200, status);
            assertNotEquals(auth1, auth2);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    void subscriptionLoginBad() {
        try {
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"dana@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth1 = apiControllerLogin.getResponse();
            assertEquals("Invalid userName or Password", auth1);
            int status = connLogin.getResponseCode();
            assertEquals(400, status);
            urlLogin = new URL("http://localhost:8080/login");
            apiControllerLogin = new APIController(urlLogin);
            connLogin = apiControllerLogin.getConn();
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"navit@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth2 = apiControllerLogin.getResponse();
            assertEquals("Invalid userName or Password", auth2);
            status = connLogin.getResponseCode();
            assertEquals(400, status);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}