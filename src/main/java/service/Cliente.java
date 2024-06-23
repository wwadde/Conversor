package service;

import com.google.gson.Gson;
import models.ExchangeRatePair;
import models.ExchangeRateSingle;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class Cliente {

    private ExchangeRateSingle single;
    private ExchangeRatePair pair;


    public Cliente(int opcion, String simbolo) {


        // Al instanciar la URI se modifica según la decisión del usuario
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + (opcion == 1 ? "latest/" : "pair/") + simbolo);


        try (HttpClient client = HttpClient.newHttpClient()) {

            // Se crea un header para que la key no sea expuesta
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Authorization", "Bearer 8886ccad6d8be6201d793da8")
                    .build();

            // Envío del request síncrono
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();

            // Conversión del json a un objeto de clase según opción elegida
            if (opcion == 1) {
                this.single = gson.fromJson(json, ExchangeRateSingle.class);
            } else {
                this.pair = gson.fromJson(json,ExchangeRatePair.class);
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ExchangeRateSingle getSingle() {
        return single;
    }

    public ExchangeRatePair getPair() {
        return pair;
    }
}
