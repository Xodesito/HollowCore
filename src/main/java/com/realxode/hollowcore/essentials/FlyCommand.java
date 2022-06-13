package com.realxode.hollowcore.essentials;

import com.realxode.hollowcore.HollowCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static api.chat.ChatUtil.translate;

public class FlyCommand implements CommandExecutor {
    private final HollowCore plugin;

    public FlyCommand(HollowCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hollowcore.cmds.fly") || !sender.hasPermission("hollowcore.admin")) {
            sender.sendMessage(translate(plugin.getLangFile().getString("NO-PERMISSION")));
            return true;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                System.out.println("Specify a player!");
                return true;
            }
            Player player = (Player) sender;
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(translate(plugin.getLangFile().getString("FLY-DISABLED")));
                return true;
            }
            player.setAllowFlight(true);
            player.setFlying(true);
            sender.sendMessage(translate(plugin.getLangFile().getString("FLY-ENABLED")));
            return true;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(translate(plugin.getLangFile().getString("PLAYER-NOT-FOUND")));
                return true;
            }
            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                target.setFlying(false);
                target.sendMessage(translate(plugin.getLangFile().getString("FLY-DISABLED")));
                sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-FLY-DISABLED")).replace("%player%", target.getName()));
                return true;
            }
            target.setAllowFlight(true);
            target.setFlying(true);
            target.sendMessage(translate(plugin.getLangFile().getString("FLY-ENABLED")));
            sender.sendMessage(translate(plugin.getLangFile().getString("TARGET-FLY-ENABLED")).replace("%player%", target.getName()));
            return true;
        }
        return false;
    }
}
