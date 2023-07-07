package chasm.chasmpvp.papi;

import chasm.chasmpvp.cache.StatsCache;
import chasm.chasmpvp.objects.PlayerStats;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderSupport extends PlaceholderExpansion {

    private final StatsCache statsCache;

    public PlaceholderSupport(StatsCache statsCache) {
        this.statsCache = statsCache;
    }


    @Override
    public @NotNull String getIdentifier() {
        return "chasmpvp";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Chasm";
    }

    @Override
    public @NotNull String getVersion() {
        return "1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {

        // Get the warzone player
        PlayerStats profile = statsCache.getProfile(player.getUniqueId());

        // Selection
        if (params.equalsIgnoreCase("kills")) {
            return String.valueOf(profile.getKills());
        } else if (params.equalsIgnoreCase("deaths")) {
            return String.valueOf(profile.getDeaths());
        } else if (params.equalsIgnoreCase("killstreak")) {
            return String.valueOf(profile.getKillStreaks());
        } else if (params.equalsIgnoreCase("kd")) {
            return (profile.getDeaths() != 0) ? String.valueOf(profile.getKills() / profile.getDeaths()) : "0";
        }

        // Return null if it's not the correct parameters for the identifier
        return null;
    }
}