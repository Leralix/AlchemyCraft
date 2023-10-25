package org.leralix.alchemycraft.Items.Item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.Brewing.BrewingRecipe;
import org.leralix.alchemycraft.Brewing.CustomItemBrew;
import org.leralix.alchemycraft.Items.ItemKey;

public class GunpowderExplosion extends CustomItemBrew {


    private static ItemStack getItem() {
        return null;
    }

    public GunpowderExplosion(){
        super(ItemKey.GUNPOWDER_EXPLOSION, getItem());
    }


    @Override
    public void brew(BrewerInventory inventory) {
    }

    public BrewingRecipe getBrewRecipe() {
        return new BrewingRecipe(new ItemStack(Material.GUNPOWDER),new ItemStack(Material.COAL), inventory -> {


            Location location = inventory.getLocation();

            World world = location.getWorld();
            world.createExplosion(location, 5, true, true);


        },false,10,0);
    }


}