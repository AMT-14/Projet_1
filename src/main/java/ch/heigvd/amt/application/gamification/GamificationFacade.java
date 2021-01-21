package ch.heigvd.amt.application.gamification;

import ch.heig.gamification.ApiCallback;
import ch.heig.gamification.ApiException;
import ch.heig.gamification.ApiResponse;
import ch.heig.gamification.api.DefaultApi;
import ch.heig.gamification.api.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

public class GamificationFacade {

    private DefaultApi api;

    @Getter
    @Setter
    int httpStatus;

    @Getter
    @Setter
    boolean isException;

    @Getter
    @Setter
    ApiResponse apiResponse;

    @Getter
    @Setter
    ApiException apiException;

    @Getter
    @Setter
    String apiKey;



    private void getApiKey() throws IOException {
        /*BufferedReader reader = new BufferedReader(new FileReader("api-key.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();*/

        apiKey = System.getProperty("API_KEY");//stringBuilder.toString();
    }
    public GamificationFacade() throws IOException {
        getApiKey();
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("ch.heig.gamification.server.url");
        api = new DefaultApi();
        api.getApiClient().setBasePath(url);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

//        String application_name = properties.getProperty("ch.heig.gamification.app.name");
//        try{
//            api.getApiClient().setApiKey(api.getApplication(application_name).getApiKey());
//        } catch (ApiException e){
//            e.printStackTrace();
//        }

    }

    public void PostEvent(String userId, EventType eventType, ApiCallback<Void> callback) throws ApiException{
//        Event event = new Event(, userId, "propertiesString");
        Event event = new Event()
                .name(eventType.name)
                .inGamifiedAppUserId(userId)
                //.creationDateTime(Date.from(Instant.now()))
                .properties("propertiesString");
        api.registerEventAsync(event, callback);

    }

    public DefaultApi getApi() {
        return api;
    }

}
