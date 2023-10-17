package org.leralix.alchemycraft.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.leralix.alchemycraft.AlchemyCraft;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class getItem extends SubCommand {
    @Override
    public String getName() {
        return "getitem";
    }


    @Override
    public String getDescription() {
        return "null";
    }
    @Override
    public String getSyntax() {
        return "/alchdebug getitem <item>";
    }

    @Override
    public List<String> getTabCompleteSuggestions(Player player, String[] args){
        List<String> suggestions = new ArrayList<>();
        if (args.length == 2) {

            for (ItemKey item : AlchemyCraft.getItemManager().getall().keySet()) {
                suggestions.add(item.toString());
            }




        }
        return suggestions;
    }
    @Override
    public void perform(Player player, String[] args){

        ItemKey itemKey = ItemKey.valueOf(args[1]);
        CustomItem item = AlchemyCraft.getItemManager().get(itemKey);
        if(item != null){
            player.getInventory().addItem(item.itemStack);
        }

    }
}


