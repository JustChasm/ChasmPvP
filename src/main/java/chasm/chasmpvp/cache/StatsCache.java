package chasm.chasmpvp.cache;

import chasm.chasmpvp.objects.PlayerStats;
import chasm.chasmpvp.storage.StatsStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsCache {

    private final StatsStorage statsStorage;
    public static Map<UUID, PlayerStats> activeStatProfiles;

    public StatsCache(StatsStorage statsStorage) {
        this.statsStorage = statsStorage;
        activeStatProfiles = new HashMap<>();
    }

    public PlayerStats getProfile(UUID identifier) {
        return activeStatProfiles.get(identifier);
    }

    public void cacheProfileFromDatabase(UUID identifier) {
        statsStorage.getObject(identifier)
                .whenComplete((playerStats, throwable) -> {
                    if (throwable != null) {
                        return;
                    }

                    if (playerStats != null) {
                        activeStatProfiles.put(playerStats.getIdentifier(), playerStats);
                    } else {
                        PlayerStats newProfile = new PlayerStats(identifier);
                        activeStatProfiles.put(newProfile.getIdentifier(), newProfile);
                    }
                });
    }

    public void removeFromCacheAndSaveToDatabase(UUID identifier) {

        // Get the profile and remove it from cache
        PlayerStats profile = activeStatProfiles.remove(identifier);

        // Null check
        if (profile == null) {
            return;
        }

        // Save it to the database
        statsStorage.saveAsync(profile);
    }

}

