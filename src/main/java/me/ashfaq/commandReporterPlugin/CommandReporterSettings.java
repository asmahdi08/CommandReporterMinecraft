package me.ashfaq.commandReporterPlugin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class CommandReporterSettings {

    private final static CommandReporterSettings instance = new CommandReporterSettings();

    private File file;
    private YamlConfiguration config;

    private String webhookUrl;
    private String webhookUsername;
    private String webhookAvatarURL;
    private String embedColor;
    private String embedIconURL;
    private String embedFooterText;
    private String embedFooterIconURL;
    private String adminRole;
    private List<String> suspiciousCommands;

    private static final Logger logger = JavaPlugin.getPlugin(CommandReporterPlugin.class).getLogger();



    private CommandReporterSettings() {

    }

    public void load() {
        file = new File(CommandReporterPlugin.getInstance().getDataFolder(), "config.yml");

        if (!file.exists()) {
            CommandReporterPlugin.getInstance().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            logger.severe("Failed to load config file");
            throw new RuntimeException(e);

        }

        webhookUrl = config.getString("webhook-url");
        webhookUsername = config.getString("webhook-username");
        webhookAvatarURL = config.getString("webhook-avatar-url");
        embedColor = config.getString("embed-color");
        embedIconURL = config.getString("embed-icon-url");
        embedFooterText = config.getString("embed-footer-text");
        embedFooterIconURL = config.getString("embed-footer-icon-url");
        adminRole = config.getString("admin-role");
        suspiciousCommands = config.getStringList("suspicious-commands");
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            logger.severe("Failed to save config file");
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setValue(String path, Object value) {
        config.set(path, value);
        save();
    }


    public static CommandReporterSettings getInstance() {
        return instance;
    }


    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getWebhookUsername() {
        return webhookUsername;
    }

    public String getWebhookAvatarURL() {
        return webhookAvatarURL;
    }

    public String getEmbedColor() {
        return embedColor;
    }

    public String getEmbedIconURL() {
        return embedIconURL;
    }

    public String getEmbedFooterText() {
        return embedFooterText;
    }

    public String getEmbedFooterIconURL() {
        return embedFooterIconURL;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public List<String> getSuspiciousCommands() {
        return suspiciousCommands;
    }
}
