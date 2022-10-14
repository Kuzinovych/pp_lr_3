package com.droid;

import com.droid.ability.DroidWithAbility;

import java.util.ArrayList;

/**
 * hits not very painful, but can heal its droids
 */

public class  Healer extends DroidWithAbility {
    @Override
    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 55;
        this.damage = 5;
        this.accuracy = 50;
        this.isAbilityFriendly = true;
    }

    public Healer(String name) {
        super(name);
    }

    @Override
    public void useAbility(Droid droidToAffect, ArrayList<Droid> teamToAffect) {
        droidToAffect.getHeal(5);
        System.out.println("\u001B[33m" + droidToAffect + " was healed with 5 hp" + "\u001B[0m");
    }
}
