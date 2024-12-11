package com.spectrasonic.deathMessage;

import com.spectrasonic.deathMessage.Commands.ReviveCommand;
import com.spectrasonic.deathMessage.Listeners.PlayerDeathListener;
import com.spectrasonic.deathMessage.Services.ConfigService;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    private ConfigService configService;

    @Override
    public void onEnable() {
        instance = this;

        registerServices();
        registerListeners();
        registerCommands();
        getLogger().info("DeathSpectator habilitado correctamente.");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeathSpectator deshabilitado.");
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("revive")).setExecutor(new ReviveCommand());
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    public void registerServices() {
        configService = new ConfigService(this);
        configService.loadConfiguration();
    }
}