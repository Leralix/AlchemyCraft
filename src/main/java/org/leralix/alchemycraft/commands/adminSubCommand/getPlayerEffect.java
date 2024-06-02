package org.leralix.alchemycraft.commands.adminSubCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.leralix.alchemycraft.commands.SubCommand;
import org.leralix.alchemycraft.customEffect.CustomEffectsManager;


import java.util.ArrayList;
import java.util.List;


public class getPlayerEffect extends SubCommand {
    @Override
    public String getName() {
        return "getplayereffect";
    }


    @Override
    public String getDescription() {
        return "null";
    }
    @Override
    public String getSyntax() {
        return "/alchdebug getplayereffect <player>";
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

        player.sendMessage(CustomEffectsManager.getAllEffects().toString());

    }
}


