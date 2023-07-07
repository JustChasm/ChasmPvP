package chasm.chasmpvp.listener.cache;

import chasm.chasmpvp.cache.StatsCache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LeaveListener implements Listener {

    private final StatsCache statsCache;

    public LeaveListener(StatsCache statsCache) {
        this.statsCache = statsCache;
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        // Get the UUID of the player
        UUID uuid = event.getPlayer().getUniqueId();

        // Remove the player from cache and save
        statsCache.removeFromCacheAndSaveToDatabase(uuid);




    }

    public void test() {

    }
}
