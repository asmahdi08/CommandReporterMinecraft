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
import org.jetbrains.annotations.NotNull;

public class Discord_WH {

    static String webhookurl = "https://discord.com/api/webhooks/1296440357722001419/5R7Mji5GtfzeerHAjeQoS6o2zXs0qrkqTp-ekBDi6j-72075BCO2penB3UolHPXDsWEJ";

    public void sendRequest(@NotNull String webhookUrl, @NotNull String playerName, @NotNull String commandText, @NotNull Boolean suspicion) {

        String titleText = "Command Executed";
        String msgText = "";
        String adminRole = "everyone";
        String embedColor = "65280";
        String webhookAvatarURL = "https://i.imgur.com/wSeLqyM.jpeg";
        String embedIconURL = "https://i.imgur.com/ZTuP6fGb.jpg";
        String footerText = "";
        String footerIconURL = "";

        if(suspicion) {
            msgText = "@" + adminRole;
        }

        URI requestUri = null;
        try {
            requestUri = new URI(webhookUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if(suspicion) {
            titleText = "Suspicious Command Executed";
        }


        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(requestUri)
                .header("Content-Type", "application/json")
                .header("User-Agent", "CommandReporterPlugin-Minecraft")
                .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                        "\t\"username\": \"Command Reporter Minecraft\",\n" +
                        "\t\"avatar_url\": \""+webhookAvatarURL+"\",\n" +
                        "\t\"content\": \""+msgText+"\",\n" +
                        "\t\"embeds\": [\n" +
                        "\t\t{\n" +
                        "\t\t\t\"author\": {\n" +
                        "\t\t\t\t\"name\": \""+ playerName + "\",\n" +
                        "\t\t\t\t\"icon_url\": \""+embedIconURL+"\"\n" +
                        "\t\t\t},\n" +
                        "\t\t\t\"title\": \""+titleText+"\",\n" +
                        "\t\t\t\"description\": \"Player executed the following command **"+commandText+"**.\",\n" +
                        "\t\t\t\"color\": "+embedColor+"\n" +
                        "\t\t\t\"footer\": {\n" +
                        "\t\t\t\t\"text\": \""+ footerText + "\",\n" +
                        "\t\t\t\t\"icon_url\": \""+ footerIconURL + "\",\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}"))
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println("Status: " + postResponse.statusCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    /*
    public static void main(String[] args) {
        new Discord_WH().sendRequest(webhookurl, "Bara","/fill", true);
    }
     */

}
