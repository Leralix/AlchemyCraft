package org.leralix.alchemycraft.Brewing;

import org.bukkit.block.BrewingStand;

import java.util.HashSet;
import java.util.Set;

public class BrewRegisterer {


    private static final Set<BrewingStand> brewingStands = new HashSet<>();


    public static void registerBrewingStand(BrewingStand brewingStand) {
        brewingStands.add(brewingStand);
    }

    public static void unregisterBrewingStand(BrewingStand brewingStand) {
        brewingStands.remove(brewingStand);
    }

    public static boolean isRegistered(BrewingStand brewingStand) {
        return brewingStands.contains(brewingStand);
    }





}
