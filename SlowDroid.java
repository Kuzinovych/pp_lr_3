package com.droid;

/**
 * often misses, but when hit, than minuses 15 hp from victim
 */

public class SlowDroid extends Droid {
    @Override
    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 55;
        this.damage = 15;
        this.accuracy = 30;
    }

    public SlowDroid(String name) {
        super(name);
    }
}
