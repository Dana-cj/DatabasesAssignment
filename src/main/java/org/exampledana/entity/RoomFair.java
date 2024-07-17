package org.exampledana.entity;

import org.exampledana.Season;

public class RoomFair {
    private int id;
   private double value;
   private Season season;


    public RoomFair(int id, double value, String season) {
        this.id =id;
        this.season = Season.valueOf(season);
        this.value = (this.season.equals(Season.ON))? value : value * 75/100;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getSeason() {
        return season.toString();
    }

    @Override
    public String toString() {
        return "RoomFair: " +
                "id=" + id +
                ", value=" + value +
                ", season='" + season + '\'' +
                "\n";
    }
}
