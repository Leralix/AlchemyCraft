package org.leralix.alchemycraft.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.leralix.alchemycraft.commands.subcommands.Test;
import org.leralix.alchemycraft.commands.subcommands.GetItem;

import java.util.ArrayList;
import java.util.List;

import static org.leralix.alchemycraft.commands.CommandManager.TabCompleter;

public class DebugCommandManager implements CommandExecutor, TabExecutor, TabCompleter {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public DebugCommandManager(){
        subCommands.add(new Test());
        subCommands.add(new GetItem());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player p){

            if (args.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(p, args);

                        //PlayerDataStorage.saveStats();
                        return true;
                    }
                }
                p.sendMessage("--------------------------------");
                for (int i = 0; i < getSubcommands().size(); i++){
                    p.sendMessage(getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                }
                p.sendMessage("--------------------------------");
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        return TabCompleter(sender, args, subCommands);
    }

    public List<SubCommand> getSubcommands(){
        return subCommands;
    }


}
