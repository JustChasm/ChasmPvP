package chasm.chasmpvp.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayerStats extends StorageObject<UUID> {

    private int kills;
    private int deaths;
    private int killStreaks;
    private float KDR;

    public PlayerStats(UUID identifier) {
        super(identifier);
    }
}
