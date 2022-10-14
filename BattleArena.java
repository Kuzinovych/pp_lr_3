package com.battle;

import com.droid.Droid;
import com.droid.ability.DroidWithAbility;
import java.util.ArrayList;
import java.util.Random;
import static com.main.Main.getUserString;

public class BattleArena {
    private ArrayList<Droid> playerTeam;
    private ArrayList<Droid> computerTeam;
    private Droid attacker;
    private Droid defender;
    private int currentRound = 0;
    private ArrayList<Droid> attackerTeam;

    public BattleArena(ArrayList<Droid> playerTeam, ArrayList<Droid> computerTeam) {
        this.playerTeam = playerTeam;
        this.computerTeam = computerTeam;
    }

    /**
     * method startFight not only start fight, but also keeps it going
     */
    public void startFight() {
        do {
            prepareRound();
            printRoundInfo(doFight());
            useAbility();
            /**
             * there are two "for", that update debufs for each droid
             * @see Droid#updateDebufs()
             */
            for (int i = 0; i < playerTeam.size(); i++) {
                playerTeam.get(i).updateDebufs();
            }
            for (int i = 0; i < computerTeam.size(); i++) {
                computerTeam.get(i).updateDebufs();
            }
            dropAllDeadDroids(playerTeam);
            dropAllDeadDroids(computerTeam);
        } while (!playerTeam.isEmpty() && !computerTeam.isEmpty());
        printWinner();
    }

    /**
     * Prints strings to show the start of new round and calls initFight method
     * @see BattleArena#initFighters()
     */
    private void prepareRound() {
        initFighters();
        currentRound++;
        System.out.println("------------------");
        System.out.println("Round " + currentRound);
    }

    /**
     * allows attacker just to hit, but not to use ability
     * @return how much defender was damaged in hp
     */
    private int doFight() { return defender.getHit(attacker.fight()); }

    /**
     * this method using random decides which droid will attack and which will be attacked
     */
    private void initFighters() {
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = getPlayerDroidManually();
            defender = getComputerDroidManually();
            attackerTeam = playerTeam;
        } else {
            attacker = getComputerDroidAuto();
            defender = getPlayerDroidAuto();
            attackerTeam = computerTeam;
        }
    }

    /**
     * prints info about result of doFight method
     * @see BattleArena#doFight()
     */
    private void printRoundInfo(int actualDamage) {
        System.out.println(defender.getName() + " get hit with " + actualDamage + " damage");
        System.out.println("Defender " + defender);
        System.out.println("Attacker " + attacker);
    }

    /**
     * if attacker has ability, it can use it
     */
    private void useAbility() {
        if ((attacker instanceof DroidWithAbility) && (attackerTeam == playerTeam)) {
            System.out.println("Ability time!-----");
            if (((DroidWithAbility) attacker).isAbilityFriendly) {
                ((DroidWithAbility)attacker).useAbility(getPlayerDroidManually(), playerTeam);
            }
            else {
                ((DroidWithAbility)attacker).useAbility(getComputerDroidManually(), computerTeam);
            }
            System.out.println("------------------");
        } else if ((attacker instanceof DroidWithAbility) && (attackerTeam == computerTeam)) {
            System.out.println("Ability time!-----");
            if (((DroidWithAbility) attacker).isAbilityFriendly) {
                ((DroidWithAbility)attacker).useAbility(getComputerDroidAuto(), computerTeam);
            }
            else {
                ((DroidWithAbility)attacker).useAbility(getPlayerDroidAuto(), playerTeam);
            }
            System.out.println("------------------");
        }
    }

    /**
     * allows computer to choose droid from player's team
     */
    private Droid getPlayerDroidAuto() {
        Random random = new Random();
        int index = random.nextInt(playerTeam.size());
        return playerTeam.get(index);
    }

    /**
     * allows computer to choose its droid
     */
    private Droid getComputerDroidAuto() {
        Random random = new Random();
        int index = random.nextInt(computerTeam.size());
        return computerTeam.get(index);
    }

    /**
     * allows player to choose his/her droid
     */
    private Droid getPlayerDroidManually() {
        String userName;
        Droid userDroid;
        System.out.println("Choose your droid:");
        System.out.println(playerTeam);
        System.out.println("Enter name of right droid to choose:");
        do {
            userName = getUserString();
            for (Droid playerDroid:
                 playerTeam) {
                if (playerDroid.getName().equals(userName)) {
                    userDroid = playerDroid;
                    return userDroid;
                }
            }
            System.out.println("Enter correct name:");
        } while (true);
    }

    /**
     * allows player to choose droid from computer's team
     */
    private Droid getComputerDroidManually() {
        String userName;
        Droid userDroid;
        System.out.println("Choose computer's droid:");
        System.out.println(computerTeam);
        System.out.println("Enter name of right droid to choose:");
        do {
            userName = getUserString();
            for (Droid computerDroid:
                    computerTeam) {
                if (computerDroid.getName().equals(userName)) {
                    userDroid = computerDroid;
                    return userDroid;
                }
            }
            System.out.println("Enter correct name:");
        } while (true);
    }

    /**
     * droid can be dead not only because of bullet
     * it can die from fire, that it caught two rounds ago
     * or a fragment of a grenade, which wasn't even for him, but for droid near him
     */
    private void dropAllDeadDroids(ArrayList<Droid> droidTeam) {
        for (int i = 0; i < droidTeam.size(); i++) {
            if (!droidTeam.get(i).isAlive()) {
                while((i < droidTeam.size()) && !droidTeam.get(i).isAlive()) {
                    droidTeam.remove(i);
                }
            }
        }
    }

    /**
     * loser is team, which has no alive droids (all dead are removed from team)
     * but if there are no droids in each team, program will also print "There are no winners."
     */
    private void printWinner() {
        if (playerTeam.isEmpty() && !computerTeam.isEmpty()) {
            System.out.println("Computer win!");
        }
        else if (!playerTeam.isEmpty() && computerTeam.isEmpty()) {
            System.out.println("You win!");
        }
        else {
            System.out.println("There are no winners.");
        }
    }
}