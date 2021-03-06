package net.kunmc.lab.onlykanjichat;

import net.kunmc.lab.onlykanjichat.event.AnvilUseEvent;
import net.kunmc.lab.onlykanjichat.event.ChatEvent;
import net.kunmc.lab.onlykanjichat.event.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnlyKanjiChat extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getLogger().info("Enable Kanji Chat!!");

        // event Listener
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new SignChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new AnvilUseEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("Disable Only Kanji Chat");
    }
}
