package org.leralix.alchemycraft.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.leralix.alchemycraft.commands.SubCommand;


import java.util.ArrayList;
import java.util.List;


public class Test extends SubCommand {
    @Override
    public String getName() {
        return "test";
    }


    @Override
    public String getDescription() {
        return "null";
    }
    @Override
    public String getSyntax() {
        return "/alchdebug test";
    }

    @Override
    public List<String> getTabCompleteSuggestions(Player player, String[] args){
        List<String> suggestions = new ArrayList<>();
        if (args.length == 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                suggestions.add(p.getName());
            }
        }
        return suggestions;
    }
    @Override
    public void perform(Player player, String[] args){

        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("item.minecraft.test.new_sword");
        itemStack.setItemMeta(itemMeta);
        player.getInventory().addItem(itemStack);


    }
}


