package main;

import main.CMDHandler.CMDHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static final String index = "[§c!§f] ";
    PluginDescriptionFile pdf = this.getDescription();

    public void onEnable() {
        pdf.getCommands().keySet().forEach($->{
            Objects.requireNonNull(getCommand($)).setExecutor(new CMDHandler());
            Objects.requireNonNull(getCommand($)).setTabCompleter(new CMDHandler());
        });
        Bukkit.getPluginManager().registerEvents(new EventHandler(),this);
    }
    public void onDisable() {

    }
}