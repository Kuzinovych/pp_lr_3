package com.battle;

import com.droid.*;
import com.main.Main;

import java.util.ArrayList;
import java.util.Random;

import static com.main.Main.getUserString;

public class Battle {
    public BattleArena battleArena;
    /**
     * the team of player
     */
    private ArrayList<Droid> myTeam;
    /**
     * the team of "AI"(computer)
     */
    private ArrayList<Droid> enemyTeam;
    public Battle(int size) {
        myTeam = generateTeamManually(size);
        enemyTeam = generateTeamAuto(size);
        battleArena = new BattleArena(myTeam, enemyTeam);
    }

    /**
     * creates computer's droids and add they to its team
     * uses method generateName to give computer's droids their names
     * @see Battle#generateName()
     * @param size - size of computer's team
     * @return dynamic array with droid (computer's team)
     */
    private ArrayList<Droid> generateTeamAuto(int size) {
        ArrayList<Droid> team = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            String autoName;
            do {
                autoName = generateName();
                if (nameIsUnique(autoName, team)) break;
            } while (true);
            Droid autoDroid;
            switch(random.nextInt(6)) {
                case 1:
                    autoDroid = new SpeedDroid(autoName);
                    break;
                case 2:
                    autoDroid = new SlowDroid(autoName);
                    break;
                case 3:
                    autoDroid = new Healer(autoName);
                    break;
                case 4:
                    autoDroid = new Pirodroid(autoName);
                    break;
                case 5:
                    autoDroid = new Bomber(autoName);
                    break;
                default:
                    autoDroid = new Droid(autoName);
            }
            team.add(autoDroid);
        }
        return team;
    }

    /**
     * allows player to create his team of droids
     * uses getString method
     * @see Main#getUserString()
     * @param size - size of computer's team
     * @return dynamic array with droid (player's team)
     */
    private ArrayList<Droid> generateTeamManually(int size) {
        ArrayList<Droid> team = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String userName;
            String userChoice;
            System.out.println("Enter name of your new droid:");
            do {
                userName = getUserString();
                if (nameIsUnique(userName, team)) break;
                System.out.println("Your droid name must be unique!");
            } while (true);
            Droid autoDroid;
            System.out.println("Enter name of class of your droid:");
            userChoice = getUserString();
            switch(userChoice) {
                case "SpeedDroid":
                    autoDroid = new SpeedDroid(userName);
                    break;
                case "SlowDroid":
                    autoDroid = new SlowDroid(userName);
                    break;
                case "Healer":
                    autoDroid = new Healer(userName);
                    break;
                case "Pirodroid":
                    autoDroid = new Pirodroid(userName);
                    break;
                case "Bomber":
                    autoDroid = new Bomber(userName);
                    break;
                default:
                    autoDroid = new Droid(userName);
            }
            team.add(autoDroid);
        }
        return team;
    }

    /**
     * generates names like "q9", "o2", "h7"
     * @return generated name for droid
     */
    private String generateName() {
        Random random = new Random();
        String generatedName = "";
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        String numbers = "0123456789";
        generatedName += letters.charAt(random.nextInt(letters.length()));
        generatedName += numbers.charAt(random.nextInt(numbers.length()));
        return generatedName;
    }

    /**
     * every name of droid must be unique, so it checks, if fresh-generated or typed name is original
     * @return boolean, that shows, if name isn't used already
     */
    private boolean nameIsUnique(String name,ArrayList<Droid> team) {
        boolean result = true;
        for (Droid droid:
             team) {
            if (name.equals(droid.getName())) {
                result = false;
                break;
            }
        }
        return result;
    }
}