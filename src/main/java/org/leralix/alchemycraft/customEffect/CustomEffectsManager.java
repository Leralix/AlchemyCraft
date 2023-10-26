package org.leralix.alchemycraft.customEffect;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CustomEffectsManager {

    private static final HashMap<String, List<CustomEffectKey>> PlayersEffect = new HashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);



    public static void addEffect(Player player, CustomEffectKey effect){
        String playerName = player.getUniqueId().toString();

        if(PlayersEffect.containsKey(playerName)){
            PlayersEffect.get(playerName).add(effect);
        }else{
            PlayersEffect.put(playerName, List.of(effect));
        }
    }

    public static void addTimedEffect(Player player, CustomEffectKey effect, int timeInSecond){
        player.sendMessage("add effect: " + effect.toString() + " for " + timeInSecond + " seconds");
        String playerName = player.getUniqueId().toString();
        if(PlayersEffect.containsKey(playerName)){
            PlayersEffect.get(playerName).add(effect);
        }else{
            PlayersEffect.put(playerName, List.of(effect));
        }
        scheduler.schedule(() -> removeEffect(player, effect), timeInSecond, TimeUnit.SECONDS);
    }

    public static void removeEffect(Player player, CustomEffectKey effect){
        player.sendMessage("remove effect: " + effect.toString());
        String playerName = player.getUniqueId().toString();

        if(PlayersEffect.containsKey(playerName)){
            player.sendMessage("same name");
            player.sendMessage(PlayersEffect.toString());

            PlayersEffect.get(playerName).remove(effect);
        }
    }

    public static boolean hasEffect(Player player, CustomEffectKey effect){
        String playerName = player.getUniqueId().toString();
        if(PlayersEffect.containsKey(playerName)){
            return PlayersEffect.get(playerName).contains(effect);
        }
        return false;
    }


}
