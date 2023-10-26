package org.leralix.alchemycraft.customEffect;

import net.kyori.adventure.text.Component;

import java.awt.*;

public enum CustomEffectKey {

    MIDAS_TOUCH(Component.translatable("alchemy_craft.effect.midas_touch"));

    final Component name;

    CustomEffectKey(Component _name){
        this.name = _name;
    }

    public String getName(){
        return "";
    }

}
