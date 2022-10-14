package com.droid.ability;

import com.droid.Droid;

public abstract class DroidWithAbility extends Droid implements Ability {
    public boolean isAbilityFriendly;
    public DroidWithAbility(String name) {
        super(name);
    }
}