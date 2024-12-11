package com.spectrasonic.deathMessage.Listeners;

import com.spectrasonic.deathMessage.Services.SpectatorService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final SpectatorService spectatorService;

    public PlayerDeathListener() {
        this.spectatorService = new SpectatorService();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.hasPermission("deathspectator.bypass")) return;

        spectatorService.setPlayerSpectator(player);
    }
}