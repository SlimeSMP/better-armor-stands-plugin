package foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.listeners.ArmorStandBreakListener;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.listeners.ArmorStandPlaceListener;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.listeners.ArmorStandRightClickListener;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.managers.PoseManager;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.util.RecipeUtil;

public final class BetterArmorStandsPlugin extends JavaPlugin {

    private PoseManager poseManager;

    private final NamespacedKey baseplateLessArmorStandRecipeKey = new NamespacedKey(this, "baseplate_less_armor_stand");

    public PoseManager getPoseManager() {
        return poseManager;
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (!getConfig().getBoolean("plugin-enabled")) {
            onDisable();
            return;
        }

        poseManager = new PoseManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ArmorStandPlaceListener(this), this);
        pluginManager.registerEvents(new ArmorStandRightClickListener(this), this);

        if (getConfig().getBoolean("baseplate.baseplate-less-recipe")) {
            pluginManager.registerEvents(new ArmorStandBreakListener(), this);

            RecipeUtil.removeRecipe(Material.ARMOR_STAND);

            ItemStack armorStand = new ItemStack(Material.ARMOR_STAND, 1);

            ShapedRecipe baseplateLessRecipe = new ShapedRecipe(baseplateLessArmorStandRecipeKey, armorStand);
            baseplateLessRecipe.shape("SSS", " S ", "S S");
            baseplateLessRecipe.setIngredient('S', Material.STICK);

            Bukkit.addRecipe(baseplateLessRecipe);
        }
    }
}
