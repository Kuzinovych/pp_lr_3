package com.droid;

import com.droid.ability.DroidWithAbility;

import java.util.ArrayList;

/**
 * can't hit, but can throw bomb
 */

public class Bomber extends DroidWithAbility {
    @Override
    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 40;
        this.damage = 0;
        this.accuracy = 100;
        this.isAbilityFriendly = false;
    }

    public Bomber(String name) {
        super(name);
    }

    @Override
    public void useAbility(Droid droidToAffect, ArrayList<Droid> teamToAffect) {
        droidToAffect.getHit(5);
        System.out.println("\u001B[33m" + droidToAffect +
                "was damaged with 5 hp by bomb" + "\u001B[0m");
        int index = 0;
        for (int i = 0; i < teamToAffect.size(); i++) {
            if (droidToAffect.getName().equals(teamToAffect.get(i).getName())) {
                index = i;
                break;
            }
        }
        if ((index - 1) >= 0) {
            teamToAffect.get(index - 1).getHit(2);
            System.out.println("\u001B[33m" + teamToAffect.get(index - 1) +
                    "was damaged with 2 hp by bomb" + "\u001B[0m");
        }
        if ((index + 1) < teamToAffect.size()) {
            teamToAffect.get(index + 1).getHit(2);
            System.out.println("\u001B[33m" + teamToAffect.get(index + 1) +
                    "was damaged with 2 hp by bomb" + "\u001B[0m");
        }
    }
}
