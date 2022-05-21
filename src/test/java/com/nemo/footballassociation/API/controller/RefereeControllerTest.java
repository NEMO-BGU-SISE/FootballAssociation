package com.nemo.footballassociation.API.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RefereeControllerTest {
    private URL url;
    private HttpURLConnection conn;
    private Referee refereeGood;
    private Referee refereeBad1;
    private Referee refereeBad2;
    private Referee refereeBad3;
    private Referee refereeBad4;
    private Referee refereeBad5;
    private Referee refereeBad6;
    private Referee refereeBad7;
    private Referee refereeBad8;
    private Referee refereeBad9;
    private Referee refereeBad10;
    private Referee refereeBad11;

    @BeforeEach
    void setUp() {
        try {
            url = new URL("http://localhost:8080/referee");
            conn = APIController.getConn(url);
            conn = APIController.getConn(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        refereeGood = new Referee("Maor", "Maor@Maor.com", "maorMaor12", "Basic");
//        refereeBad1 = new Referee("Maor1", "Maor@Maor.com", "maorMaor12", "Basic");
//        refereeBad2 = new Referee("Maor", "Maor@", "maorMaor12", "Basic");
//        refereeBad3 = new Referee("Maor", "@Maor.com", "maorMaor12", "Basic");
//        refereeBad4 = new Referee("Maor", "Maor.com", "maorMaor12", "Basic");
//        refereeBad5 = new Referee("Maor", "Maor@Maor.com", "maoraor12", "Basic");
//        refereeBad6 = new Referee("Maor", "Maor@Maor.com", "maorMaor", "Basic");
//        refereeBad7 = new Referee("Maor", "Maor@Maor.com", "MAORMAOR", "Basic");
//        refereeBad8 = new Referee("Maor", "Maor@Maor.com", "123456789", "Basic");
//        refereeBad9 = new Referee("Maor", "Maor@Maor.com", "123456", "Basic");
//        refereeBad10 = new Referee("Maor", "Maor@Maor.com", "maor", "Basic");
//        refereeBad11 = new Referee("Maor", "Maor@Maor.com", "MAOR", "Basic");
    }

    @AfterEach
    void tearDown() {
        conn.disconnect();
    }

    @Test
    void saveReferee() {
        try {
            APIController.setRequestMethod("POST");
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonStringGood = mapper.writeValueAsString(refereeGood);
            String jsonStringBad1 = mapper.writeValueAsString(refereeBad1);
            String jsonStringBad2 = mapper.writeValueAsString(refereeBad2);
            String jsonStringBad3 = mapper.writeValueAsString(refereeBad3);
            String jsonStringBad4 = mapper.writeValueAsString(refereeBad4);
            String jsonStringBad5 = mapper.writeValueAsString(refereeBad5);
            String jsonStringBad6 = mapper.writeValueAsString(refereeBad6);
            String jsonStringBad7 = mapper.writeValueAsString(refereeBad7);
            String jsonStringBad8 = mapper.writeValueAsString(refereeBad8);
            String jsonStringBad9 = mapper.writeValueAsString(refereeBad9);
            String jsonStringBad10 = mapper.writeValueAsString(refereeBad10);
            String jsonStringBad11 = mapper.writeValueAsString(refereeBad11);
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(jsonStringGood.getBytes("utf-8"));
            int status = conn.getResponseCode();
            int x = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void getAllReferees() {
        try {
            APIController.setRequestMethod("GET");
            int status = conn.getResponseCode();
            assertEquals(200, status);
            BufferedReader reader;
            String line;
            StringBuffer responseContent = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            String jsonString = responseContent.toString();
            JsonArray element = (JsonArray) new JsonParser().parse(jsonString);

            // SQLite connection string
            String url = "jdbc:sqlite:NemoDb";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String sql = "select s.id, s.name, s.user_name, s.password, r.referee_training " +
                    "from referee r inner join subscription s on r.id=s.id";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int ind = 0;
                ObjectMapper mapper = new ObjectMapper();
                // loop through the result set
                while (rs.next()){
                    int numColumns = rsmd.getColumnCount();
                    JSONObject obj = new JSONObject();
                    for (int i=1; i<=numColumns; i++) {
                        String column_name = rsmd.getColumnName(i);
                        obj.put(column_name, rs.getObject(column_name));
                    }
                    JsonObject jsonObject = (JsonObject) element.get(ind++);

                    mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                    Map<String, Object> map1 = mapper.readValue(obj.toString(), HashMap.class);
                    String json1 = mapper.writeValueAsString(map1);
                    Map<String, Object> map2 = mapper.readValue(jsonObject.toString(), HashMap.class);
                    String json2 = mapper.writeValueAsString(map2);
                    assertEquals(json1, json2);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRefereeById() {

    }

    @Test
    void getRefereeByUserName() {

    }

    @Test
    void updateReferee() {

    }

    @Test
    void deleteReferee() {

    }
}