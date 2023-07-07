package chasm.chasmpvp;

import chasm.chasmpvp.cache.StatsCache;
import chasm.chasmpvp.listener.cache.JoinListener;
import chasm.chasmpvp.listener.cache.LeaveListener;
import chasm.chasmpvp.listener.gameplay.KillListener;
import chasm.chasmpvp.manager.StatsManager;
import chasm.chasmpvp.papi.PlaceholderSupport;
import chasm.chasmpvp.storage.StatsStorage;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChasmPvP extends JavaPlugin {

    @Getter private static ChasmPvP instance;
    private StatsStorage statsStorage;
    @Getter private StatsCache statsCache;
    private StatsManager statsManager;

    @Override
    public void onEnable() {

        // Define singleton
        instance = this;

        // Registering config
        saveDefaultConfig();

        // Registering storage
        statsStorage = new StatsStorage();

        // Registering cache
        statsCache = new StatsCache(statsStorage);

        // Register the placeholder api hook
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderSupport(statsCache).register();
        }

        // Call the methods
        registerManagers();
        registerListeners();
    }

    private void registerManagers() {
        statsManager = new StatsManager();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(statsCache), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(statsCache), this);
        getServer().getPluginManager().registerEvents(new KillListener(statsManager, statsCache), this);
    }
}
