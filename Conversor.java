import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
//import java.util.Map;


//class Resposta{
    //double amount;
   // String base;
    //Map<String, Double> rates;
//}

public class Conversor{
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String moedaOrigem = "";
        String moedaDestino = "";

        System.out.println("Valor para conversão: ");
        double valor = leitura.nextDouble();

        System.out.println("Escolha a moeda de origem:");
        System.out.println("1 - BRL\n");
        System.out.println("2 - USD\n");
        System.out.println("3 - EUR\n");
        System.out.println("4 - AUD\n");
        System.out.println("5 - CAD\n");
        System.out.println("6 - DKK\n");
        System.out.println("7 - GBP\n");



        int opcaoOrigem = leitura.nextInt();
        

    
        switch (opcaoOrigem) {
            case 1 -> moedaOrigem = "BRL";
            case 2 -> moedaOrigem = "USD";
            case 3 -> moedaOrigem = "EUR";
            case 4 -> moedaOrigem ="AUD";
            case 5 -> moedaOrigem ="CAD";
            case 6 -> moedaOrigem ="DKK";
            case 7 -> moedaOrigem ="GBP";
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        System.out.println("Escolha a moeda de destino:");
        System.out.println("1 - BRL\n");
        System.out.println("2 - USD\n");
        System.out.println("3 - EUR\n");
        System.out.println("4 - AUD\n");
        System.out.println("5 - CAD\n");
        System.out.println("6 - DKK\n");
        System.out.println("7 - GBP\n");

        int opcaoDestino = leitura.nextInt();

        switch (opcaoDestino) {
            case 1 -> moedaDestino = "BRL";
            case 2 -> moedaDestino = "USD";
            case 3 -> moedaDestino = "EUR";
            case 4 -> moedaDestino = "AUD";
            case 5 -> moedaDestino = "CAD";
            case 6 -> moedaDestino = "DKK";
            case 7 -> moedaDestino = "GBP";
        }
        if(moedaOrigem.equals(moedaDestino)){
            System.out.println("Resultado: " + valor + " " + moedaOrigem + " equivalem a " + valor + " " + moedaDestino);
            leitura.close();
            return;
        }

       
        String lugar = "https://api.frankfurter.app/latest?amount=" + valor +  "&from=" + moedaOrigem + "&to=" + moedaDestino;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(lugar)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            //JsonObject rates = jsonObject.getAsJsonObject("rates");

            if(jsonObject.has("rates")){
                JsonObject rates = jsonObject.getAsJsonObject("rates");
                double resultado = rates.get(moedaDestino).getAsDouble();
            System.out.println("Resultado: " + valor + " " + moedaOrigem + " equivalem a " + resultado + " " + moedaDestino);
            }
            else{
                System.out.println("Não foi possível fazer a conversão");
            }

            //double resultado=rates.get(moedaDestino).getAsDouble();

            //double resultado = rates.get(moedaDestino).getAsDouble();


        } catch (Exception e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
        
    }
   
}
