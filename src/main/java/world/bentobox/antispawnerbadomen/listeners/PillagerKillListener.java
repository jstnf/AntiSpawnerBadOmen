package world.bentobox.antispawnerbadomen.listeners;

import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import world.bentobox.antispawnerbadomen.AntiSpawnerBadOmen;

public class PillagerKillListener implements Listener {

    private final AntiSpawnerBadOmen addon;

    public PillagerKillListener(AntiSpawnerBadOmen addon) {
        this.addon = addon;
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        if (!(e.getDamager() instanceof Player && e.getEntity() instanceof Pillager)) return;

        Pillager pillager = (Pillager) e.getEntity();
        if (!pillager.isPatrolLeader()) return;
        if (pillager.getHealth() > e.getFinalDamage()) return; // The entity isn't dead

        addon.getKillTracker().add(e.getDamager().getUniqueId(), !pillager.getScoreboardTags().contains(AntiSpawnerBadOmen.NATURAL_TAG));
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void on(EntityPotionEffectEvent e) {
        if (e.isCancelled()) return;
        if (!(e.getEntity() instanceof Player) || e.getCause() != EntityPotionEffectEvent.Cause.PATROL_CAPTAIN) return;
        e.setCancelled(addon.getKillTracker().isLastKillUnnatural(e.getEntity().getUniqueId()));
    }
}