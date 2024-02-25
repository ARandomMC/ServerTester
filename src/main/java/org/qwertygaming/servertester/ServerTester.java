package org.qwertygaming.servertester;

import org.bukkit.plugin.java.JavaPlugin;

public final class ServerTester extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            // Plugin startup logic
            getCommand("status").setExecutor(new StatusCommand());
            getLogger().info("ServerTester has been enabled");
        } catch (Exception e) {
            getLogger().severe("Error during plugin initialization:");
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutting Down ServerTester");
    }
}
