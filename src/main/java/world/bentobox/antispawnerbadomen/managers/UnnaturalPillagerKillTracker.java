package world.bentobox.antispawnerbadomen.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Holds information on whether a player's last killed patrol leader
// was a naturally spawned patrol leader or if they were unnaturally spawned
public class UnnaturalPillagerKillTracker {

    private final Map<UUID, Boolean> trackedKills;

    public UnnaturalPillagerKillTracker() {
        trackedKills = new HashMap<>();
    }

    public void add(UUID uniqueId, boolean unnatural) {
        trackedKills.put(uniqueId, unnatural);
    }

    public boolean isLastKillUnnatural(UUID uniqueId) {
        return trackedKills.getOrDefault(uniqueId, false);
    }

    public void onQuit(Player p) {
        trackedKills.remove(p.getUniqueId());
    }
}