import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Cliente;
import models.ExchangeRatePair;
import models.ExchangeRateSingle;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        while (true) {

            Scanner scanner = new Scanner(System.in);

            String x = """
                      --   Bienvenido al conversor de Monedas    --
                                        
                                1. Conversión estándar
                                2. Conversión por pares
                                3. Salir
                    """;
            System.out.println(x);
            int decision;

            do {
                System.out.println("-- Digite un numero válido del tipo de búsqueda que desea --");
                decision = scanner.nextInt();
            } while (decision < 1 || decision > 3);

            // Conversion Estandar
            if (decision == 1) {
                String y = """
                          *--       Conversión estándar        --*
                        """;
                System.out.println(y);
                scanner.nextLine();
                // Regex para tomar solo input alfabético sin importar mayúsculas
                String regex = "^[a-zA-Z]+$";
                boolean matches;
                String simbolo1;

                // Itera hasta que se el input sea válido
                do {
                    System.out.println("-- Escriba un código válido de la Moneda a buscar --");
                    simbolo1 = scanner.nextLine();
                    matches = Pattern.matches(regex, simbolo1);
                } while (!matches);

                Cliente cliente = new Cliente(decision, simbolo1);
                ExchangeRateSingle single = cliente.getSingle();
                if (single.getResult().equalsIgnoreCase("success")) {

                    // Imprime el diccionario reducido
                    var diccionario = single.getTasasMonedasReducido();
                    // Contrucción del String para posteriormente transmitirlo al logger
                    StringBuilder informacionString = new StringBuilder();
                    informacionString.append("Moneda buscada: ").append(single.getMonedaBuscada()).append("\n");
                    diccionario.forEach((key, value) -> {
                        informacionString.append(key).append(": ").append(value).append("\n");
                    });
                    logger.info(informacionString.toString());


                } else {
                    System.out.println("MONEDA NO ENCONTRADA");
                }

            // Conversion por Pares
            } else if (decision == 2) {

                String z = """
                             *--     Conversión por pares    --*
                           -- Separado por slashs  /  escriba:
                         -- Primero el codigo de la moneda de referencia
                        -- Segundo el codigo de la moneda a convertir
                         -- Por ultimo el numero de unidades a convertir
                        """;

                // Ejemplo USD/COP/10
                String regex = "^[A-Za-z]+/[A-Za-z]+/\\d+$";
                System.out.println(z);
                scanner.nextLine();
                String simbolo2;
                boolean matches;
                // Itera hasta que el input sea válido
                do {
                    System.out.println("Escriba una búsqueda válida,  EJEMPLO: USD/COP/10  ");
                    simbolo2 = scanner.nextLine();
                    matches = Pattern.matches(regex, simbolo2);
                } while (!matches);


                    Cliente cliente = new Cliente(decision, simbolo2);
                    ExchangeRatePair pair = cliente.getPair();

                    if (pair.getResult().equalsIgnoreCase("success")){
                        // Extrae todos los caracteres no digitos
                        int numerosExtraidos = Integer.parseInt(simbolo2.replaceAll("\\D+",""));
                        pair.setCantidad(numerosExtraidos);
                        logger.info(pair.toString());
                    }
                    else {
                        System.out.println("Ocurrió un error en la búsqueda, verifique la validez de los Símbolos");
                    }

            } else {
                break;
            }


        }

    }
}
