package world.bentobox.antispawnerbadomen;

import world.bentobox.antispawnerbadomen.listeners.PillagerKillListener;
import world.bentobox.antispawnerbadomen.listeners.PillagerSpawnListener;
import world.bentobox.antispawnerbadomen.listeners.QuitListener;
import world.bentobox.antispawnerbadomen.managers.UnnaturalPillagerKillTracker;
import world.bentobox.bentobox.api.addons.Addon;

public class AntiSpawnerBadOmen extends Addon {

    public static final String UNNATURAL_TAG = "AntiSpawnerBadOmenUnnatural";

    private UnnaturalPillagerKillTracker killTracker;

    @Override
    public void onEnable() {
        killTracker = new UnnaturalPillagerKillTracker();
        getServer().getPluginManager().registerEvents(new PillagerKillListener(this), getPlugin());
        getServer().getPluginManager().registerEvents(new PillagerSpawnListener(), getPlugin());
        getServer().getPluginManager().registerEvents(new QuitListener(this), getPlugin());
    }

    @Override
    public void onDisable() { }

    /* getset */
    public UnnaturalPillagerKillTracker getKillTracker() {
        return killTracker;
    }
}