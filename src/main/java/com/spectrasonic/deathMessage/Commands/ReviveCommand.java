package com.spectrasonic.deathMessage.Commands;

import com.spectrasonic.deathMessage.Main;
import com.spectrasonic.deathMessage.Services.SpectatorService;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReviveCommand implements CommandExecutor {

    private final SpectatorService spectatorService;
    private final Main plugin;

    public ReviveCommand() {
        this.plugin = Main.getInstance();
        this.spectatorService = new SpectatorService();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("deathspectator.admin"))) {
            sender.sendMessage("§cNo tienes permiso.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUso: /revive <player|all|reload>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getConfigService().loadConfiguration();
                sender.sendMessage("§aConfiguracion recargada.");
                break;
            case "all":
                Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.getGameMode() == GameMode.SPECTATOR)
                        .forEach(spectatorService::revivePlayer);
                break;
            default:
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    spectatorService.revivePlayer(target);
                } else {
                    sender.sendMessage("§cJugador no encontrado.");
                }
        }
        return true;
    }
}