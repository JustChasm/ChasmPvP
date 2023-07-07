package chasm.chasmpvp.listener.gameplay;

import chasm.chasmpvp.ChasmPvP;
import chasm.chasmpvp.cache.StatsCache;
import chasm.chasmpvp.manager.StatsManager;
import chasm.chasmpvp.objects.PlayerStats;
import chasm.chasmpvp.utils.DeathParticleUtils;
import chasm.chasmpvp.utils.HexUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener {

    private final StatsManager statsManager;
    private final StatsCache statsCache;
    private final String killedActionbar = ChasmPvP.getInstance().getConfig().getString("killed-actionbar");

    public KillListener(StatsManager statsManager, StatsCache statsCache) {
        this.statsManager = statsManager;
        this.statsCache = statsCache;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        // Get the killer and victim
        Player victim = event.getPlayer();
        Player killer = victim.getKiller();

        // Make sure it is a player they died to
        if (killer == null) {
            return;
        }

        // Get the stat profiles
        PlayerStats victimProfile = statsCache.getProfile(victim.getUniqueId());
        PlayerStats killerProfile = statsCache.getProfile(killer.getUniqueId());

        // Handle the profile stats
        statsManager.addDeaths(1, victimProfile);
        statsManager.addKills(1, killerProfile);
        statsManager.addKillStreaks(1, killerProfile);
        statsManager.resetKillStreaks(victimProfile);

        // Send the action bar to the killer
        killer.sendActionBar(HexUtils.c(killedActionbar)
                .replace("%killstreak_amount%", String.valueOf(killerProfile.getKillStreaks()))
                .replace("%player%", victim.getName()));

        DeathParticleUtils.spawnSpiralParticles(victim.getLocation());

        victim.playSound(victim.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1.0F, 1.0F);
        killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }
}
