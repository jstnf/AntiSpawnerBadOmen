package world.bentobox.antispawnerbadomen.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import world.bentobox.antispawnerbadomen.AntiSpawnerBadOmen;

public class QuitListener implements Listener {

    private final AntiSpawnerBadOmen addon;

    public QuitListener(AntiSpawnerBadOmen addon) {
        this.addon = addon;
    }

    @EventHandler
    public void on(PlayerQuitEvent e) {
        addon.getKillTracker().onQuit(e.getPlayer());
    }
}