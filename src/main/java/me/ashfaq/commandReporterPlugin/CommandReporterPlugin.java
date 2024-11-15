package me.ashfaq.commandReporterPlugin;

import me.ashfaq.commandReporterPlugin.Webhooks.Discord_WH;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class CommandReporterPlugin extends JavaPlugin implements Listener {

    private static CommandReporterPlugin instance;
    private String webhookUrl;
    private List<String> suspiciousCommands;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getLogger().info("CommandReporter plugin started");
        CommandReporterSettings.getInstance().load();
        getLogger().info("CommandReporter plugin webhookUrl: " + CommandReporterSettings.getInstance().getWebhookUrl());
        getLogger().info(CommandReporterSettings.getInstance().getWebhookUsername());

        getServer().getPluginManager().registerEvents(this, this);
        webhookUrl = CommandReporterSettings.getInstance().getWebhookUrl();
        suspiciousCommands = CommandReporterSettings.getInstance().getSuspiciousCommands();
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CommandReporter plugin stopped");
    }

    public static CommandReporterPlugin getInstance() {
        return instance;
    }

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent event){
        String Player = event.getPlayer().getName();
        String command = event.getMessage();
        String firstWord = command.split(" ")[0].toLowerCase();

        boolean suspicion = suspiciousCommands.stream().anyMatch(cmd -> cmd.equalsIgnoreCase(firstWord));
        new Discord_WH().sendRequest(webhookUrl, Player, command, suspicion);
    }
}
