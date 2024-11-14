package me.ashfaq.commandReporterPlugin;

import me.ashfaq.commandReporterPlugin.Webhooks.Discord_WH;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandReporterPlugin extends JavaPlugin implements Listener {

    static String webhookUrl = "https://discord.com/api/webhooks/1296440357722001419/5R7Mji5GtfzeerHAjeQoS6o2zXs0qrkqTp-ekBDi6j-72075BCO2penB3UolHPXDsWEJ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("CommandReporter plugin started boiiii");;

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("CommandReporter plugin stopped");
    }

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent event){
        String Player = String.valueOf(event.getPlayer().getName());
        String command = event.getMessage();
        Boolean Suspicion = false;
        new Discord_WH().sendRequest(webhookUrl, Player, command, Suspicion);
    }
}
