package org.leralix.alchemycraft.commands.adminSubCommand;

import org.bukkit.entity.Player;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class GetItem extends SubCommand {
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
            suggestions.addAll(ItemManager.getAll().keySet());
        }
        return suggestions;
    }
    @Override
    public void perform(Player player, String[] args){

        String itemKey = args[1];
        CustomItem item = ItemManager.get(itemKey);
        if(item == null){
            player.sendMessage("Item not found");
            return;
        }
        player.sendMessage("Item " + itemKey + " added to your inventory");
        player.getInventory().addItem(item.getItemStack());

    }
}


