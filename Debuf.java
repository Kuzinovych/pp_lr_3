package com.droid.debuf;

import com.droid.Droid;

/**
 * debuf is temporary effect, that can last few rounds
 * if time of this effect is passed, it can affect droid anymore
 */
public abstract class Debuf {
    protected int usedTime = 0;
    protected int maxTime;
    protected Droid droidToDebuf;
    public Debuf(Droid droidToDebuf) {
        this.droidToDebuf = droidToDebuf;
        setMaxTime();
    }

    public void setMaxTime() {
    }

    public void useDebuf() {
    }

    public boolean canBeUsed() {
        return usedTime < maxTime;
    }

    public void passTime() {
        usedTime++;
    }
}
