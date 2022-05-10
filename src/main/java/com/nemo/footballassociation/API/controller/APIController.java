package com.nemo.footballassociation.API.controller;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Locale;

public class APIController {
    private static HttpURLConnection conn;

    public static HttpURLConnection getConn(URL url) throws IOException {
        conn = (HttpURLConnection) url.openConnection();
        return conn;
    }

    public static void setRequestMethod(String method) throws ProtocolException {
        conn.setRequestMethod(method.toUpperCase(Locale.ROOT));
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
    }
}
