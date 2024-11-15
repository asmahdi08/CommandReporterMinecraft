package me.ashfaq.commandReporterPlugin.Webhooks;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import me.ashfaq.commandReporterPlugin.CommandReporterPlugin;
import me.ashfaq.commandReporterPlugin.CommandReporterSettings;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Discord_WH {

    private static final Logger logger = JavaPlugin.getPlugin(CommandReporterPlugin.class).getLogger();


    public void sendRequest(@NotNull String webhookUrl, @NotNull String playerName, @NotNull String commandText, boolean suspicion) {

        String titleText = "Command Executed";
        String msgText = "";
        //String webhookURL = CommandReporterSettings.getInstance().getWebhookUrl();
        String adminRole = CommandReporterSettings.getInstance().getAdminRole();
        String embedColor = CommandReporterSettings.getInstance().getEmbedColor();
        String webhookAvatarURL = CommandReporterSettings.getInstance().getWebhookAvatarURL();
        String embedIconURL = CommandReporterSettings.getInstance().getEmbedIconURL();
        String footerText = CommandReporterSettings.getInstance().getEmbedFooterText();
        String footerIconURL = CommandReporterSettings.getInstance().getEmbedFooterIconURL();



        if(suspicion) {
            msgText = "@" + adminRole;
        }

        URI requestUri;
        try {
            requestUri = new URI(webhookUrl);
        } catch (URISyntaxException e) {
            logger.severe("Invalid URI: " + webhookUrl);
            throw new RuntimeException("Failed to create URI", e);
        }

        if(suspicion) {
            titleText = "Suspicious Command Executed";
        }

        String jsonPayload = "{\n" +
                "  \"username\": \"Command Reporter Minecraft\",\n" +
                "  \"avatar_url\": \"" + webhookAvatarURL + "\",\n" +
                "  \"content\": \"" + msgText + "\",\n" +
                "  \"embeds\": [\n" +
                "    {\n" +
                "      \"author\": {\n" +
                "        \"name\": \"" + playerName + "\",\n" +
                "        \"icon_url\": \"" + embedIconURL + "\"\n" +
                "      },\n" +
                "      \"title\": \"" + titleText + "\",\n" +
                "      \"description\": \"Player executed the following command **" + commandText + "**.\",\n" +
                "      \"color\": " + embedColor + ",\n" +
                "      \"footer\": {\n" +
                "        \"text\": \"" + footerText + "\",\n" +
                "        \"icon_url\": \"" + footerIconURL + "\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";


        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(requestUri)
                .header("Content-Type", "application/json")
                .header("User-Agent", "CommandReporterPlugin-Minecraft")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();


        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + postResponse.statusCode());
        } catch (IOException | InterruptedException e) {
            logger.severe("Failed to send request to Discord");
            throw new RuntimeException(e);
        }


    }

/*
    public static void main(String[] args) {
        String webhookURL = CommandReporterSettings.getInstance().getWebhookUrl();
        new Discord_WH().sendRequest("https://discord.com/api/webhooks/<webhookID>", "test","/test", true);
    }

 */


}
