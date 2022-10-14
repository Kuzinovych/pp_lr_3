package com.droid;

/**
 * SpeedDroid can shoot three bullets in one round
 */

public class SpeedDroid extends Droid {
    protected int bullets;
    @Override
    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 50;
        this.damage = 5;
        this.accuracy = 30;
        this.bullets = 3;
    }
    public SpeedDroid(String name) {
        super(name);
    }
    @Override
    public int fight() {
        int actualDamage = 0;
        for (int i = 0; i < bullets; i++) {
            actualDamage += super.fight();
        }
        return actualDamage;
    }
}