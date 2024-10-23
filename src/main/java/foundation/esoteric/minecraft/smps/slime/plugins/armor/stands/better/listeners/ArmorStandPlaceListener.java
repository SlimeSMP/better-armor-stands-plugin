package foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.BetterArmorStandsPlugin;

public class ArmorStandPlaceListener implements Listener {

    private final BetterArmorStandsPlugin plugin;

    public ArmorStandPlaceListener(BetterArmorStandsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArmorStandSpawn(@NotNull CreatureSpawnEvent event) {
        if (event.getEntity() instanceof ArmorStand stand) {
            YamlConfiguration configuration = (YamlConfiguration) plugin.getConfig();

            if (configuration.getBoolean("arms")) {
                stand.setArms(true);
            }

            if (configuration.getBoolean("baseplate.baseplate-less-recipe")) {
                stand.setBasePlate(false);
            }
        }
    }
}
