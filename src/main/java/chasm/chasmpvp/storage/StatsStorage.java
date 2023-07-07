package chasm.chasmpvp.storage;

import chasm.chasmpvp.objects.PlayerStats;
import chasm.chasmpvp.storage.types.JSONStorage;

import java.util.UUID;

public class StatsStorage extends JSONStorage<UUID, PlayerStats> {

    public StatsStorage() {
        super(PlayerStats.class, "stats");
    }

}
