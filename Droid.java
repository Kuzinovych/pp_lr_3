package com.droid;

import com.droid.debuf.Debuf;

import java.util.ArrayList;
import java.util.Random;

/**
 * the main, classic class of droid
 * all other droids are descended from him
 */
public class Droid {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected int accuracy;
    /**
     * all debufs are stored in this list
     */
    protected ArrayList<Debuf> listOfDebufs = new ArrayList<Debuf>();

    public Droid(String name) {
        this.name = name;
        setDefaultCharacteristics();
    }

    protected void setDefaultCharacteristics() {
        this.hp = this.maxHp = 40;
        this.damage = 10;
        this.accuracy = 50;
    }

    /**
     * if you follow the "canon", this method allows droid to shoot
     * @return damage or 0, if droid missed
     */
    public int fight() {
        int actualDamage = 0;
        Random random = new Random();
        if (random.nextInt(100) <= accuracy) {
            actualDamage += damage;
        }
        return actualDamage;
    }

    /**
     * allows droid to take damage from another droid
     * @param actualDamage
     * @return also actualDamage
     */
    public int getHit(int actualDamage) {
        hp -= actualDamage;
        if (hp < 0) {
            hp = 0;
        }
        return actualDamage;
    }

    /**
     * allows droid to take repair from another droid
     */
    public void getHeal(int heal) {
        hp += heal;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public boolean isAlive() {
        return (hp > 0);
    }

    @Override
    public String toString() {
        return name + " " + this.getClass().getSimpleName() + " " + hp;
    }

    public String getName() {
        return name;
    }

    public void addDebuf(Debuf debuf) {
        listOfDebufs.add(debuf);
    }

    /**
     * allows all debufs to affect droid, and then deletes all debufs, that are can't be used no more
     */
    public void updateDebufs() {
        for(int i = 0; i < listOfDebufs.size(); i++) {
            listOfDebufs.get(i).useDebuf();
            listOfDebufs.get(i).passTime();
        }
        for (int i = 0; i < listOfDebufs.size(); i++) {
            if (!listOfDebufs.get(i).canBeUsed()) {
                while((i < listOfDebufs.size()) && !listOfDebufs.get(i).canBeUsed()) {
                    listOfDebufs.remove(i);
                }
            }
        }
    }
}