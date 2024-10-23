package foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.listeners;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmorStandBreakListener implements Listener {

    @EventHandler
    public void onArmorStandBreak(@NotNull EntityDeathEvent event) {
        if (event.getEntity() instanceof ArmorStand stand && stand.hasBasePlate()) {
            ItemStack smoothStoneSlab = new ItemStack(Material.SMOOTH_STONE_SLAB, 1);

            stand.getWorld().dropItemNaturally(stand.getLocation(), smoothStoneSlab);
        }
    }
}
