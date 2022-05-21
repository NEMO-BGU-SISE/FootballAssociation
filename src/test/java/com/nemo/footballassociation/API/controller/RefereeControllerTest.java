package com.nemo.footballassociation.API.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nemo.footballassociation.Contracts.Enums.RefereeTraining;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Referee;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RefereeControllerTest {
    private URL url;
    private URL urlDelete;
    private URL urlLogin;
    private HttpURLConnection conn;
    private HttpURLConnection connDelete;
    private HttpURLConnection connLogin;
    private APIController apiController;
    private APIController apiControllerDelete;
    private APIController apiControllerLogin;
    private ArrayList<Referee> refereesGood = new ArrayList<>();
    private HashMap<Referee,String> refereesBad = new HashMap<>();

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

        refereesGood.add(new Referee("Maor", "Maor@Maor.com", "maorMaor12", RefereeTraining.BasicTraining));
        refereesGood.add(new Referee("elran", "Elran@Elran.com", "maorMaor12", RefereeTraining.AdvanceTraining));
        refereesGood.add(new Referee("noa", "Noa@Noa.com", "maorMaor12", RefereeTraining.ExpertTraining));
        refereesGood.add(new Referee("or", "Or@Or.com", "maorMaor12", RefereeTraining.ExpertTraining));
        refereesGood.add(new Referee("elran", "Maor123@Maor.com", "maorMaor12", RefereeTraining.BasicTraining));
        refereesBad.put(new Referee("Maor1", "Maor@Maor.com", "maorMaor12", RefereeTraining.BasicTraining), "Error: Name contains numbers");
        refereesBad.put(new Referee("Maor", "Maor@", "maorMaor12", RefereeTraining.BasicTraining), "Error: The username is not a valid Email address");
        refereesBad.put(new Referee("Maor", "@Maor.com", "maorMaor12", RefereeTraining.BasicTraining), "Error: The username is not a valid Email address");
        refereesBad.put(new Referee("Maor", "Maor.com", "maorMaor12", RefereeTraining.AdvanceTraining), "Error: The username is not a valid Email address");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "maoraor12", RefereeTraining.BasicTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "maorMaor", RefereeTraining.ExpertTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "MAORMAOR", RefereeTraining.BasicTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "123456789", RefereeTraining.AdvanceTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "123456", RefereeTraining.BasicTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "maor", RefereeTraining.ExpertTraining), "Error: The password is not secure enough");
        refereesBad.put(new Referee("Maor", "Maor@Maor.com", "MAOR", RefereeTraining.BasicTraining), "Error: The password is not secure enough");
    }

    @AfterEach
    void tearDown() {
        try {
            if(conn.getResponseCode() == 200) {
                conn.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int countRows() {
        try {
            // SQLite connection string
            String url = "jdbc:sqlite:NemoDb";
            Connection connection = null;
            connection = DriverManager.getConnection(url);
            String sql = "select count(*) from referee";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = rs.getInt(1);
            connection.close();
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Test
    void deleteRefereeGood(){
        try {
            int rowsBeforeInsert = countRows();
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth = apiControllerLogin.getResponse();
            Referee referee = new Referee("Maor", "Maor@Maor.com", "maorMaor12", RefereeTraining.BasicTraining);
            apiController = new APIController(url);
            conn = apiController.getConn();
            apiController.setRequestMethod("POST", auth);
            String jsonRefereeString = String.format("{\n\"name\": \"%s\",\n \"userName\": \"%s\",\n \"password\": \"%s\",\n \"refereeTraining\": \"%s\"\n }\n",
                    referee.getName(), referee.getUserName(), referee.getPassword(), referee.getRefereeTraining());
            apiController.setBody(jsonRefereeString);
            String response = apiController.getResponse();
            int rowsAfterInsert = countRows();
            assertEquals(rowsBeforeInsert + 1, rowsAfterInsert);
            int start = response.indexOf(":");
            int end = response.indexOf(",");
            int id = Integer.parseInt(response.substring(start+1, end));
            referee.setId(id);
            urlDelete = new URL("http://localhost:8080/referee/" + referee.getId());
            apiControllerDelete = new APIController(urlDelete);
            connDelete = apiControllerDelete.getConn();
            apiControllerDelete.setRequestMethod("DELETE", auth);
            int status = connDelete.getResponseCode();
            assertEquals(200, status);
            int rowsAfterDelete = countRows();
            assertEquals(rowsBeforeInsert, rowsAfterDelete);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteRefereeBad() {
        try {
            int rowsBeforeInsert = countRows();
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth = apiControllerLogin.getResponse();
            urlDelete = new URL("http://localhost:8080/referee/" + 1000);
            apiControllerDelete = new APIController(urlDelete);
            connDelete = apiControllerDelete.getConn();
            apiControllerDelete.setRequestMethod("DELETE", auth);
            String response = apiControllerDelete.getResponse();
            assertEquals("Referee doesn't exists!.", response);
            int status = connDelete.getResponseCode();
            assertEquals(400, status);
            int rowsAfterDelete = countRows();
            assertEquals(rowsBeforeInsert, rowsAfterDelete);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveRefereeGood() {
        try {
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth = apiControllerLogin.getResponse();
            String jsonRefereeString;
            for (Referee referee: refereesGood){
                int rowsBeforeInsert = countRows();
                apiController = new APIController(url);
                conn = apiController.getConn();
                apiController.setRequestMethod("POST", auth);
                jsonRefereeString = String.format("{\n\"name\": \"%s\",\n \"userName\": \"%s\",\n \"password\": \"%s\",\n \"refereeTraining\": \"%s\"\n }\n",
                                    referee.getName(), referee.getUserName(), referee.getPassword(), referee.getRefereeTraining());
                apiController.setBody(jsonRefereeString);
                String response = apiController.getResponse();
                int index = response.indexOf(",");
                String responseStr = "{" + response.substring(index+1);
                jsonRefereeString = String.format("{\"name\":\"%s\",\"password\":\"%s\",\"user_name\":\"%s\",\"referee_training\":\"%s\"}",
                        referee.getName(),DigestUtils.md5Hex(referee.getPassword()), referee.getUserName(),  referee.getRefereeTraining());
                assertEquals(jsonRefereeString, responseStr);
                int status = conn.getResponseCode();
                assertEquals(201, status);
                int rowsAfterInsert = countRows();
                assertEquals(rowsBeforeInsert + 1, rowsAfterInsert);
                int start = response.indexOf(":");
                int end = response.indexOf(",");
                int id = Integer.parseInt(response.substring(start+1, end));
                referee.setId(id);
                urlDelete = new URL("http://localhost:8080/referee/" + referee.getId());
                apiControllerDelete = new APIController(urlDelete);
                connDelete = apiControllerDelete.getConn();
                apiControllerDelete.setRequestMethod("DELETE", auth);
                connDelete.getResponseCode();
                int rowsAfterDelete = countRows();
                assertEquals(rowsBeforeInsert, rowsAfterDelete);
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void saveRefereeBad(){
        try {
            apiControllerLogin.setRequestMethod("POST", "");
            apiControllerLogin.setBody("{\n\"userName\": \"admin@nemo.com\",\n\"password\": \"admin\"\n}\n");
            String auth = apiControllerLogin.getResponse();
            String jsonRefereeString;
            for (Map.Entry<Referee, String> entry: refereesBad.entrySet()){
                Referee referee = entry.getKey();
                String error = entry.getValue();
                int rowsBefore = countRows();
                apiController = new APIController(url);
                conn = apiController.getConn();
                apiController.setRequestMethod("POST", auth);
                jsonRefereeString = String.format("{\n\"name\": \"%s\",\n \"userName\": \"%s\",\n \"password\": \"%s\",\n \"refereeTraining\": \"%s\"\n }\n",
                        referee.getName(), referee.getUserName(), referee.getPassword(), referee.getRefereeTraining());
                apiController.setBody(jsonRefereeString);
                String response = apiController.getResponse();
                assertEquals(error, response);
                int status = conn.getResponseCode();
                assertEquals(400, status);
                int rowsAfter = countRows();
                assertEquals(rowsBefore, rowsAfter);
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllReferees() {
        try {
            apiController.setRequestMethod("GET", "");
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
            finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRefereeByIdGood() {
        try {
            url = new URL("http://localhost:8080/referee/1");
            apiController = new APIController(url);
            conn = apiController.getConn();
            int status = conn.getResponseCode();
            assertEquals(200, status);
            String response = apiController.getResponse();
            assertEquals("{\"id\":1,\"name\":\"maya\",\"password\":\"42f749ade7f9e195bf475f37a44cafcb\",\"user_name\":\"maya@nemo.com\",\"referee_training\":\"BasicTraining\"}", response);
            url = new URL("http://localhost:8080/referee/2");
            apiController = new APIController(url);
            conn = apiController.getConn();
            status = conn.getResponseCode();
            assertEquals(200, status);
            response = apiController.getResponse();
            assertEquals("{\"id\":2,\"name\":\"or\",\"password\":\"42f749ade7f9e195bf475f37a44cafcb\",\"user_name\":\"or@nemo.com\",\"referee_training\":\"BasicTraining\"}", response);
            url = new URL("http://localhost:8080/referee/3");
            apiController = new APIController(url);
            conn = apiController.getConn();
            status = conn.getResponseCode();
            assertEquals(200, status);
            response = apiController.getResponse();
            assertEquals("{\"id\":3,\"name\":\"maor\",\"password\":\"42f749ade7f9e195bf475f37a44cafcb\",\"user_name\":\"maor@nemo.com\",\"referee_training\":\"BasicTraining\"}", response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRefereeByIdBad(){
        try {
            url = new URL("http://localhost:8080/referee/1000");
            apiController = new APIController(url);
            conn = apiController.getConn();
            int status = conn.getResponseCode();
            assertEquals(400, status);
            String response = apiController.getResponse();
            assertEquals("Referee Id does not exists", response);
            url = new URL("http://localhost:8080/referee/1001");
            apiController = new APIController(url);
            conn = apiController.getConn();
            status = conn.getResponseCode();
            assertEquals(400, status);
            response = apiController.getResponse();
            assertEquals("Referee Id does not exists", response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRefereeByUserNameGood() {
        try {
            url = new URL("http://localhost:8080/referee/userName/maya@nemo.com");
            apiController = new APIController(url);
            conn = apiController.getConn();
            int status = conn.getResponseCode();
            assertEquals(200, status);
            String response = apiController.getResponse();
            assertEquals("{\"id\":1,\"name\":\"maya\",\"password\":\"42f749ade7f9e195bf475f37a44cafcb\",\"user_name\":\"maya@nemo.com\",\"referee_training\":\"BasicTraining\"}", response);
            url = new URL("http://localhost:8080/referee/userName/noa@nemo.com");
            apiController = new APIController(url);
            conn = apiController.getConn();
            status = conn.getResponseCode();
            assertEquals(200, status);
            response = apiController.getResponse();
            assertEquals("Referee Id does not exists", response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateReferee() {

    }
}