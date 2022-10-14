package com.droid.ability;

import com.droid.Droid;

import java.util.ArrayList;

public interface Ability {
    void useAbility(Droid droidToAffect, ArrayList<Droid> teamToAffect);
}
