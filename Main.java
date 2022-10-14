package com.main;

import com.battle.Battle;

import java.util.Scanner;

/**
 * @author Kuzinovych Ostap KN-202
 */

public class Main {
    public static void main(String[] args) {
        Battle battle = new Battle(getUserSize());
        battle.battleArena.startFight();
    }

    /**
     * @return the string, that have more, than 0 chars and is typed on keyboard
     */
    public static String getUserString() {
        Scanner scanner = new Scanner(System.in);
        String userString;
        do {
            userString = scanner.nextLine();
            if (userString.length() != 0) break;
            System.out.println("Your userString must have more than 0 chars!");
        } while (true);
        return userString;
    }

    /**
     * @return the natural number, that is used as size of teams of droids
     */
    public static int getUserSize() {
        Scanner scanner = new Scanner(System.in);
        int userSize;
        do {
            if (scanner.hasNextInt()) {
                userSize = scanner.nextInt();
            } else {
                System.out.println("Yor number must be int");
                continue;
            }
            if (userSize > 0) break;
            System.out.println("userSize must be greater than 0!");
        } while (true);
        return userSize;
    }
}