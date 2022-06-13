package com.realxode.hollowcore.essentials;

import com.realxode.hollowcore.HollowCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static api.chat.ChatUtil.translate;

public class GamemodeCommand implements CommandExecutor {

    private final HollowCore plugin;

    public GamemodeCommand(HollowCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hollowcore.cmds.gamemode") || !sender.hasPermission("hollowcore.admin")) {
            sender.sendMessage(translate(plugin.getLangFile().getString("NO-PERMISSION")));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-NO-ARGS")).replace("%cmd%", label));
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                System.out.println("Specify a player!");
                return true;
            }
            Player player = (Player) sender;
            String gmode = args[0];
            switch (gmode.toLowerCase()) {
                case "1":
                case "c":
                case "CREATIVE":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Creative"));
                    break;
                case "0":
                case "s":
                case "SURVIVAL":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Survival"));
                    break;
                case "2":
                case "a":
                case "ADVENTURE":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Adventure"));
                    break;
                case "3":
                case "sp":
                case "SPECTATOR":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Spectator"));
                    break;
                default:
                    player.sendMessage(translate(plugin.getLangFile().getString("NO-GAMEMODE-SPECIFIED")));
                    break;
            }
        }
        if (args.length == 2) {
            String gmode = args[0];
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(translate(plugin.getLangFile().getString("PLAYER-NOT-FOUND")));
                return true;
            }
            switch (gmode.toLowerCase()) {
                case "1":
                case "c":
                case "CREATIVE":
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Creative"));
                    sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-GAMEMODE-CHANGED"))
                            .replace("%gamemode%", "Creative").replace("%player%", player.getName()));
                    break;
                case "0":
                case "s":
                case "SURVIVAL":
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Survival"));
                    sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-GAMEMODE-CHANGED"))
                            .replace("%gamemode%", "Survival").replace("%player%", player.getName()));
                    break;
                case "2":
                case "a":
                case "ADVENTURE":
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Adventure"));
                    sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-GAMEMODE-CHANGED"))
                            .replace("%gamemode%", "Adventure").replace("%player%", player.getName()));
                    break;
                case "3":
                case "sp":
                case "SPECTATOR":
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(translate(plugin.getLangFile().getString("GAMEMODE-CHANGED")).replace("%gamemode%", "Spectator"));
                    sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-GAMEMODE-CHANGED"))
                            .replace("%gamemode%", "Spectator").replace("%player%", player.getName()));
                    break;
                default:
                    player.sendMessage(translate(plugin.getLangFile().getString("NO-GAMEMODE-SPECIFIED")));
                    break;
            }
        }
        return false;
    }
}