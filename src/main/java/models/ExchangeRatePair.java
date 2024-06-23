package models;

import com.google.gson.annotations.SerializedName;

public class ExchangeRatePair {

    private String result;

    @SerializedName("base_code")
    private String monedaReferencia;

    @SerializedName("target_code")
    private String monedaConvertida;

    @SerializedName("conversion_rate")
    private Float tasaConversion;

    @SerializedName("conversion_result")
    private Float valorTotalConversion;

    private int cantidad;

    public String getResult() {
        return result;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "[Tasa de cambio: 1 " + monedaReferencia + " = " + tasaConversion + " " + monedaConvertida + "]\n" +
                cantidad + " " + monedaReferencia + " equivalen a: " + valorTotalConversion + " " + monedaConvertida;
    }
}
