package com.realxode.hollowcore;

import api.file.FileConfig;
import com.realxode.hollowcore.essentials.FlyCommand;
import com.realxode.hollowcore.essentials.GamemodeCommand;
import com.realxode.hollowcore.essentials.SpeedCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class HollowCore extends JavaPlugin {

    private FileConfig cfgFile;
    private FileConfig langFile;

    @Override
    public void onEnable() {
        /* FILES */
        cfgFile = new FileConfig(this, "settings.yml");
        langFile = new FileConfig(this, "lang.yml");

        /* COMMANDS */
        getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("speed").setExecutor(new SpeedCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfig getCfgFile() {
        return cfgFile;
    }

    public FileConfig getLangFile() {
        return langFile;
    }
}
