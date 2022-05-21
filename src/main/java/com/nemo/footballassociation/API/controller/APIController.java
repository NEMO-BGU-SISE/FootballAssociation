package com.nemo.footballassociation.API.controller;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

public class APIController {
    private HttpURLConnection conn;

    public APIController(URL url) throws IOException {
        this.conn = (HttpURLConnection) url.openConnection();
    }

    public HttpURLConnection getConn(){
        return conn;
    }

    public void setRequestMethod(String method, String code) throws ProtocolException {
        conn.setRequestMethod(method.toUpperCase(Locale.ROOT));
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", code);
        conn.setDoOutput(true);
        conn.setUseCaches(true);
    }

    public void setBody(String body){

        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(){
        try {
            InputStream input = null;
            if(conn.getResponseCode() < 300) {
                input = conn.getInputStream();
            }
            else{
                input = conn.getErrorStream();
            }
            try(BufferedReader br = new BufferedReader(new InputStreamReader(input, "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}


