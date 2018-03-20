package pl.edu.wat.wcy.isi.siecsilowni.rest;

import com.google.gson.Gson;
import javafx.concurrent.Task;
import pl.edu.wat.wcy.isi.siecsilowni.GymApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class TimeUser {
    private static String TIME = "http://worldclockapi.com/api/json/cet/now";

    public Task<TimeAndDate> getTime() {
        return new Task<TimeAndDate>() {
            @Override
            protected TimeAndDate call() throws Exception {
                TimeAndDate time;
                URL url = null;
                try {
                    url = new URL(TIME);
                } catch (MalformedURLException e) {
                    GymApp.logger.error("Błędny adres", e);
                }

                InputStreamReader reader = null;
                try {
                    reader = new InputStreamReader(url.openStream());
                } catch (IOException e) {
                    GymApp.logger.error("Błąd związany z odczytem danych", e);
                }
                BufferedReader bufferedReader = new BufferedReader(reader);
                String response = bufferedReader.lines().collect(Collectors.joining());
                Gson g = new Gson();

                time = g.fromJson(response, TimeAndDate.class);
                return time;
            }
        };
    }
}