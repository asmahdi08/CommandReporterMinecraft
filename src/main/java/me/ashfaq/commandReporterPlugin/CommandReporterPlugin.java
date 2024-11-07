package me.ashfaq.commandReporterPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommandReporterPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("CommandReporter plugin started");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("CommandReporter plugin stopped");
    }
}
