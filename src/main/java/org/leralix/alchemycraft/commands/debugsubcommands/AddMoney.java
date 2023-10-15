package org.leralix.alchemycraft.commands.debugsubcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.leralix.alchemycraft.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;


public class AddMoney extends SubCommand {

    @Override
    public String getName() {
        return "addmoney";
    }

    @Override
    public String getDescription() {
        return "Add money to a player.";
    }

    @Override
    public String getSyntax() {
        return "/tandebug addmoney <player> <amount>";
    }
    public List<String> getTabCompleteSuggestions(Player player, String[] args){

        List<String> suggestions = new ArrayList<>();
        if (args.length == 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                suggestions.add(p.getName());
            }
        }
        if (args.length == 3) {
            suggestions.add("<amount>");
        }
        return suggestions;
    }
    @Override
    public void perform(Player player, String[] args) {
        player.locale();

    }
}