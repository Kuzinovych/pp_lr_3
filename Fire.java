package com.droid.debuf;

import com.droid.Droid;

public class Fire extends Debuf {
    @Override
    public void setMaxTime() {
        this.maxTime = 3;
    }

    public Fire(Droid droidToDebuf) {
        super(droidToDebuf);
    }

    @Override
    public void useDebuf() {
        if (canBeUsed()) {
            droidToDebuf.getHit(3);
            System.out.println("\u001B[33m" + droidToDebuf + " was grilled with 3 hp" + "\u001B[0m");
        }
    }
}
