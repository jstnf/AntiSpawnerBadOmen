package world.bentobox.antispawnerbadomen.listeners;

import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import world.bentobox.antispawnerbadomen.AntiSpawnerBadOmen;

public class PillagerSpawnListener implements Listener {

    // Detect and tag Pillager Captains that are not spawned via natural means or through Patrols
    // These tags persist through server restarts
    // Reference: https://minecraft.fandom.com/wiki/Scoreboard#Tags
    // https://www.spigotmc.org/threads/scoreboard-tag-methods.194208/#post-2027911
    @EventHandler(priority = EventPriority.MONITOR)
    public void on(CreatureSpawnEvent e) {
        if (e.isCancelled()) return;
        if (!(e.getEntity() instanceof Pillager)) return;

        Pillager pillager = (Pillager) e.getEntity();
        if (pillager.isPatrolLeader() &&
                !(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.PATROL)) {
            pillager.addScoreboardTag(AntiSpawnerBadOmen.UNNATURAL_TAG);
        }
    }
}