import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nemo.footballassociation.API.controller.APIController;
import com.nemo.footballassociation.Contracts.Modules.DbModels.Subscription;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class main {
    public static void main(String[] args) {
        try {
            BufferedReader reader;
            String line;
            StringBuffer responseContent = new StringBuffer();
            URL url = new URL("http://localhost:8080/referee");
            HttpURLConnection conn = APIController.getConn(url);
            APIController.setRequestMethod("POST");
            int status = conn.getResponseCode();
            System.out.println(status);
            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
                return;
            }
            else{
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.getClass());
            System.out.println(responseContent.toString());
            ObjectMapper mapper = new ObjectMapper();
            List<Subscription> list = mapper.readValue(responseContent.toString(), new TypeReference<List<Subscription>>(){});
            System.out.println(list);
            for (int i = 0; i < list.toArray().length; i++) {
                System.out.println(list.toArray()[i].getClass());
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
