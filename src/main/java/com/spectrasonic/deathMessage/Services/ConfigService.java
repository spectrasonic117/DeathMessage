package com.spectrasonic.deathMessage.Services;

import com.spectrasonic.deathMessage.Main;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigService {
    private final Main plugin;

    @Getter
    private String deathTitle;
    @Getter
    private String deathSubtitle;
    @Getter
    private String reviveMessage;

    public ConfigService(Main plugin) {
        this.plugin = plugin;
    }

    public void loadConfiguration() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        FileConfiguration config = plugin.getConfig();

        deathTitle = translateColorCodes(config.getString("deathspectator.settitle", "&c[❌] Has Muerto"));
        deathSubtitle = translateColorCodes(config.getString("deathspectator.setsubtitle", "&7Ahora eres espectador"));
        reviveMessage = translateColorCodes(config.getString("deathspectator.revivemessage", "&a[✔] Has sido revivido"));
    }

    private String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}