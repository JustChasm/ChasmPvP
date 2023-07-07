package chasm.chasmpvp.manager;

import chasm.chasmpvp.objects.PlayerStats;

public class StatsManager {

    // Methods to manage handling object values
    public void addKills(int amount, PlayerStats profile) {
        profile.setKills(profile.getKills() + amount);
    }

    public void addDeaths(int amount, PlayerStats profile) {
        profile.setDeaths(profile.getDeaths() + amount);
    }

    public void addKillStreaks(int amount, PlayerStats profile) {
        profile.setKillStreaks(profile.getKillStreaks() + amount);
    }

    public void takeKills(int amount, PlayerStats profile) {
        profile.setKills(profile.getKills() - amount);
    }

    public void takeDeaths(int amount, PlayerStats profile) {
        profile.setDeaths(profile.getDeaths() - amount);
    }

    public void takeKillStreaks(int amount, PlayerStats profile) {
        profile.setKillStreaks(profile.getKillStreaks() - amount);
    }

    public void resetKillStreaks(PlayerStats profile) {
        profile.setKillStreaks(0);
    }
}
