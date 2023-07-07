package chasm.chasmpvp.listener.cache;

import chasm.chasmpvp.cache.StatsCache;
import chasm.chasmpvp.manager.StatsManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    private final StatsCache statsCache;

    public JoinListener(StatsCache statsCache) {
        this.statsCache = statsCache;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        // Get the UUID of the player
        UUID uuid = event.getPlayer().getUniqueId();

        // Cache the player from the database
        statsCache.cacheProfileFromDatabase(uuid);

    }
}
