package com.spectrasonic.deathMessage.Services;

import com.spectrasonic.deathMessage.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpectatorService {

    private final ConfigService configService;

    public SpectatorService() {
        this.configService = Main.getInstance().getConfigService();
    }

    public void setPlayerSpectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.sendTitle(
                configService.getDeathTitle(),
                configService.getDeathSubtitle(),
                10, 70, 20
        );
    }

    public void revivePlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(configService.getReviveMessage());
    }
}