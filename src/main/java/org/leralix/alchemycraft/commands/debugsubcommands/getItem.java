package org.leralix.alchemycraft.commands.debugsubcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.leralix.alchemycraft.Items.CustomItem;
import org.leralix.alchemycraft.Items.ItemKey;
import org.leralix.alchemycraft.Items.ItemManager;
import org.leralix.alchemycraft.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class getItem extends SubCommand {

    @Override
    public String getName() {
        return "getitem";
    }

    @Override
    public String getDescription() {
        return "get the custom item from ";
    }

    @Override
    public String getSyntax() {
        return "/alchdebug getitem";
    }

    public List<String> getTabCompleteSuggestions(Player player, String[] args) {
        List<String> list = new ArrayList<>();
        for (ItemKey itemKey : ItemKey.values()) {
            list.add(itemKey.name());
        }
        return list;

    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1) {
            player.sendMessage(Component.text().content("You must specify an item key").color(NamedTextColor.RED));
            return;
        }
        ItemKey itemKey = ItemKey.valueOf(args[1]);

        if(itemKey == null){
            player.sendMessage(Component.text().content("This item does not exist").color(NamedTextColor.RED));
            return;
        }

        player.getInventory().addItem(ItemManager.getItemStack(itemKey));
        player.sendMessage(Component.text().content("You received the item").color(NamedTextColor.GREEN));
    }
}
