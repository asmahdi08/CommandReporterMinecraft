package me.ashfaq.commandReporterPlugin.Webhooks;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Discord_WH {

    public void sendRequest(String webhookUrl) {
        URI requestUri = null;
        try {
            requestUri = new URI(webhookUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(requestUri)
                .header("Content-Type", "application/json")
                .header("User-Agent", "CommandReporterPlugin-Minecraft")
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "   \"content\": \"Hello from your Discord webhook!\",\n" +
                        "   \"username\": \"Webhook Bot\",\n" +
                        "   \"embeds\": [\n" +
                        "     {\n" +
                        "       \"title\": \"Webhook Example\",\n" +
                        "       \"description\": \"This is an example of a Discord webhook message.\",\n" +
                        "       \"color\": 65280\n" +
                        "     }\n" +
                        "   ]\n" +
                        " }"))
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + postResponse.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        new Discord_WH().sendRequest("https://discord.com/api/webhooks/1296440357722001419/5R7Mji5GtfzeerHAjeQoS6o2zXs0qrkqTp-ekBDi6j-72075BCO2penB3UolHPXDsWEJ");
    }

}
