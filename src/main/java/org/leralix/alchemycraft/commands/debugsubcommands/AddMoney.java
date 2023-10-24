package org.leralix.alchemycraft.commands.debugsubcommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.leralix.alchemycraft.commands.SubCommand;

import java.util.List;
import java.util.Locale;


public class AddMoney extends SubCommand {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "test";
    }

    @Override
    public String getSyntax() {
        return "/test";
    }
    public List<String> getTabCompleteSuggestions(Player player, String[] args){
        return null;

    }
    @Override
    public void perform(Player player, String[] args) {

        player.sendMessage(Component.text().content(String.valueOf(player.locale())));




        if(player.locale().equals(Locale.FRANCE)){
            player.sendMessage(Component.text().content("Bonjour ").append(player.displayName().color(NamedTextColor.RED)));
        }
        else{
            player.sendMessage(Component.text().content("Hello ").append(player.displayName().color(NamedTextColor.BLUE)));
        }

    }
}