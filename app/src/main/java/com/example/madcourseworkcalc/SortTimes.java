package com.example.madcourseworkcalc;

import java.util.Comparator;
/**
 * Sort times class used to order players and their times so that they can be displayed
 * correctly on the leaderboard
 */
public class SortTimes  implements Comparator<Player> {

    public int compare(Player player1, Player player2){

        String time1 = player1.getTime();
        String time2 = player2.getTime();

        String[] timeSplit1 = time1.split(":");
        String[] timeSplit2 = time2.split(":");

        int minutes1 = Integer.parseInt(timeSplit1[0]);
        int seconds1 = Integer.parseInt(timeSplit1[1]);
        int ms1 = Integer.parseInt(timeSplit1[2]);

        int minutes2 = Integer.parseInt(timeSplit2[0]);
        int seconds2 = Integer.parseInt(timeSplit2[1]);
        int ms2 = Integer.parseInt(timeSplit2[2]);

        if(minutes1!=minutes2){
            return minutes1-minutes2;
        } else if (seconds1!=seconds2) {
            return seconds1-seconds2;
        } else {
            return  ms1-ms2;
        }

    }
}
