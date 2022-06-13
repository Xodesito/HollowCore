package com.realxode.hollowcore.essentials;

import com.realxode.hollowcore.HollowCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static api.chat.ChatUtil.translate;

public class SpeedCommand implements CommandExecutor {

    private final HollowCore plugin;

    public SpeedCommand(HollowCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hollowcore.cmds.fly") || !sender.hasPermission("hollowcore.admin")) {
            sender.sendMessage(translate(plugin.getLangFile().getString("NO-PERMISSION")));
            return true;
        }
        if (!(sender instanceof Player)) {
            System.out.println("Specify a player!");
            return true;
        }
        Player player = (Player) sender;
        double speed = 0;
        try {
            speed = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(translate(plugin.getLangFile().getString("INVALID-SPEED")));
            return true;
        }
        if (speed > 10 || speed < 0) {
            player.sendMessage(translate(plugin.getLangFile().getString("INVALID-SPEED")));
            return true;
        }
        if (player.isFlying()) {
            player.setFlySpeed((float) speed);
        } else {
            player.setFlySpeed((float) speed);
        }
        player.sendMessage(translate(plugin.getLangFile().getString("SPEED-CHANGED")).replace("%speed%", args[0]));
        return false;
    }

}
