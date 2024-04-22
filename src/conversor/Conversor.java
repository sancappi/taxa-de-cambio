package conversor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import excecao.ErroAoEfetuarConversaoException;
import java.io.FileWriter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    public void conversor(String origem, String destino, double valor) throws ErroAoEfetuarConversaoException {

        try {
            String urlMoeda = "https://v6.exchangerate-api.com/v6/b59b9b971dfe06dade8a06ff/latest/" + origem;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlMoeda)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //System.out.println(json);

            Gson gson = new GsonBuilder().create();

            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

            JsonObject objetoDesejado = jsonObject.getAsJsonObject("conversion_rates");
            double ValorDestino = objetoDesejado.get(destino).getAsDouble();

            double valorEquivalente = valor * ValorDestino;

            System.out.println("\n*******************************************");
            String texto = valor + " em " + origem + " equivale a " + valorEquivalente + " em " + destino;
            System.out.println(texto);
            FileWriter arquivoTexto = new FileWriter("taxa_de_cambio.txt");
            arquivoTexto.write(texto.toString());
            arquivoTexto.close();
            System.out.println("A informação acima está contida no arquivo taxa_de_cambio.txt. ");

        } catch(Exception e) {
            throw new ErroAoEfetuarConversaoException("Erro ao longo do processo de conversão: " + e.getMessage());
        }
    }
}