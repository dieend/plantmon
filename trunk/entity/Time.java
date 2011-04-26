/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plantmon.entity;

import java.io.Serializable;
import plantmon.states.Game;
import plantmon.states.ParentState;

/**
 *
 * @author asus
 */
public class Time implements Serializable{
    public static final int SPRING = 0;
    public static final int SUMMER = 1;
    public static final int FALL = 2;
    private static Time time;
    private int season;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private boolean blink;
    int counter;
    public static Time instance(){
        if (time == null) {
            time = new Time();
        }
        return time;
    }
    Time(){
        season = SPRING;
        year = 1;
        month = 1;
        day = 1;
        hour = 6;
        minutes = 0;
        counter = 0;
    }
    public void load(Time time){
        Time.time = time;
    }
    public synchronized void update(){
        counter+=1;
        if (counter == 100){
            minutes+=10;
            if (minutes == 60){
                hour+=1;
                minutes = 0;
            }
            counter = 0;
        }
        if (hour == 19) {
            Game.instance().goTo(ParentState.HOME, null);
        }
    }
    private String make2digit(String str){
        if (str.length()<2) str= "0"+str;
        return str;
    }
    public void changeDay(){
        hour = 6;
        minutes = 0;
        counter = 0;
        day+=1;
        if (day==30){
            day = 1;
            season +=1;
            if (season == 3){
                year += 1;
            }
            season%=3;
        }
    }
    private String separator(){
        if (counter%50==0){
            blink = !blink;
        }
        if (blink){
            return ":";
        } else {
            return " ";
        }
    }
    public synchronized String getTime(){
        String ss = (season==SUMMER)?("SUMMER"):
                    (season==FALL)?("FALL"):
                    (season == SPRING)?("SPRING"):"";
        String sy = String.valueOf(year);
        String sm = String.valueOf(month);
        String sd = make2digit(String.valueOf(day));
        String sh = make2digit(String.valueOf(hour));
        String smn = make2digit(String.valueOf(minutes));
        int w = Game.instance().getWeather();
        String weather = (w == Game.RAINY)?("Rain"):
                         (w == Game.STORM)?("Storm"):
                         (w == Game.SUNNY)?("Sunny"):"";
        return (ss+" year "+sy+", month "+sm+".\n"+" day "+sd+" "+sh+separator()+smn+" "+weather);
    }
    /**
     * @return the season
     */
    public int getSeason() {
        return season;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }
}
