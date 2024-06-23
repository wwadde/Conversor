package models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateSingle {

    private String result;

    @SerializedName("conversion_rates")
    private Map<String, Float> tasasMonedas;

    @SerializedName("base_code")
    private String monedaBuscada;


    public String getResult() {
        return result;
    }

    //    ARS - Peso argentino
    //    BOB - Boliviano boliviano
    //    BRL - Real brasileño
    //    CLP - Peso chileno
    //    COP - Peso colombiano
    //    USD - Dólar estadounidense

    public Map<String, Float> getTasasMonedasReducido() {
        Map<String, Float> nuevoMap = new HashMap<>();
        // Registrar las key y values segun las key de la lista
        List<String> list = List.of("ARS","BOB","BRL","CLP","COP","USD");
        list.forEach(moneda -> {
            nuevoMap.put(moneda,tasasMonedas.get(moneda));
        });

        return nuevoMap;
    }

    public String getMonedaBuscada() {
        return monedaBuscada;
    }

    @Override
    public String toString() {
        return "ExchangeRateSingle{" +
                "result='" + result + '\'' +
                ", tasasMonedas=" + tasasMonedas +
                ", monedaBuscada='" + monedaBuscada + '\'' +
                '}';
    }
}
