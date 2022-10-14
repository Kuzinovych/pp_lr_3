package com.droid;

import com.droid.ability.DroidWithAbility;
import com.droid.debuf.Fire;

import java.util.ArrayList;

/**
 * this droid can set enemy droid on fire
 * i.e. to impose debuf "Fire"
 * @see Fire
 */

public class Pirodroid extends DroidWithAbility {
    @Override
    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 40;
        this.damage = 0;
        this.accuracy = 100;
        this.isAbilityFriendly = false;
    }

    public Pirodroid(String name) { super(name); }

    @Override
    public void useAbility(Droid droidToAffect, ArrayList<Droid> teamToAffect) {
        Fire fire = new Fire(droidToAffect);
        droidToAffect.addDebuf(fire);
        System.out.println("\u001B[33m" + droidToAffect + " catches fire!" + "\u001B[0m");
    }
}
