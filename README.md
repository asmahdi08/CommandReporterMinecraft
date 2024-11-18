# Command Reporter Plugin

The Command Reporter Plugin is a Minecraft plugin that reports player commands to a Discord webhook. It is designed to help server administrators monitor and manage player activities by sending notifications to a specified Discord channel.

**Curseforge** : https://www.curseforge.com/minecraft/bukkit-plugins/command-reporter

## Features

- Reports all player commands to a Discord webhook.
- Highlights suspicious commands and mentions a specified admin role.
- Customizable webhook settings, including username, avatar URL, embed color, and more.
- Configurable list of suspicious commands.

## Installation

1. **Download the Plugin**: Download the latest version of the Command Reporter Plugin from the [releases page](https://github.com/your-repo/releases).

2. **Copy to Plugins Folder**: Place the downloaded `.jar` file into your server's `plugins` directory.

3. **Start the Server**: Start your Minecraft server to generate the default configuration files.

4. **Configure the Plugin**: Edit the `config.yml` file located in the `plugins/CommandReporterPlugin` directory to customize the plugin settings.

## Message Structure
![embed](https://github.com/user-attachments/assets/7aaaba7b-5127-4471-8338-0cd026be70d7)

**The plugin will send an embed message like this**


## Configuration

The `config.yml` file contains the following settings:

```yaml
# This file contains the settings for the Command Reporter Plugin.

# This section contains the settings for the plugin's Discord webhook.
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"
webhook-username: "Command Reporter"
webhook-avatar-url: "https://example.com/avatar.png"
embed-color: 65280
embed-icon-url: "https://example.com/icon.png"

# Leave both of these empty if you don't want a footer.
embed-footer-text: "Sent From Command Reporter Plugin Minecraft"
embed-footer-icon-url: "https://example.com/footer-icon.png"

# Specify the discord role that will be mentioned if a suspicious command is executed.
# DO NOT INCLUDE THE @ SYMBOL
admin-role: "admin"

# This list contains a list of all suspicious commands.
suspicious-commands:
- /op
- /deop
- /stop
- /reload
- /ban
- /ban-ip
- /kick
- /whitelist
- /whitelist add
- /whitelist remove
- /whitelist list
- /whitelist reload
- /whitelist on
- /whitelist off
- /pardon
- /pardon-ip
- /save-all
- /save-off
- /save-on
- /plugins
- /advancement
- /attribute
- /bossbar
- /clear
- /clone
- /data
- /datapack
- /debug
- /defaultgamemode
- /difficulty
- /effect
- /enchant
- /gamemode
- /gamerule
- /give
- /kill
- /locate
- /locatebiome
```



## Usage

Once the plugin is installed and configured, it will automatically start reporting player commands to the specified Discord webhook. Suspicious commands will be highlighted and the specified admin role will be mentioned.

Building the Plugin
1. Clone the Repository: Clone the repository to your local machine.
   
   ```$ git clone https://github.com/your-repo/CommandReporterPlugin.git```
   
   ```$ cd CommandReporterPlugin```
   
3. Build the Plugin: Use Maven to build the plugin.

   ```$ mvn clean package```

4. Get the JAR File: The compiled .jar file will be located in the target directory.


## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgements
SpigotMC(https://www.spigotmc.org/) - The Minecraft plugin API used in this project.
Discord-Webhook-Docs(https://discord.com/developers/docs/resources/webhook)- The docs are a must for knowing how the webhook is structured
Contact
For any questions or support, please open an issue on the GitHub repository.
