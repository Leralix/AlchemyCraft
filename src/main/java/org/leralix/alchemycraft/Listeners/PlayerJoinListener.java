package org.leralix.alchemycraft.Listeners;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.leralix.alchemycraft.AlchemyCraft;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(Component.text().content("Hello ").append(player.get(Identity.DISPLAY_NAME).get().color(NamedTextColor.RED)));

        event.getPlayer().discoverRecipes(AlchemyCraft.getPlugin().recipeKeys);


    }

}
