package sample;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Game {
    private int gameNumber;
    private int gameId;
    private Team teamA;
    private Team teamB;
    private Date playingDate;
    private String playingDateString;
    private Time playingTime;
    private String playingTimeString;

    public Game(int gameNumber, int gameId, Team teamA, Team teamB, Date playingDate, String playingDateString, Time playingTime, String playingTimeString){
        this.gameNumber = gameNumber;
        this.gameId = gameId;
        this.teamA = teamA;
        this.teamB = teamB;
        this.playingDate = playingDate;
        this.playingDateString = playingDateString;
        this.playingTime = playingTime;
        this.playingTimeString = playingTimeString;
    }

    public String outputString(){
        return "Game " + gameNumber + " with Id " + gameId + ": " + teamA.getName() + " vs " + teamB.getName() + " on the " + playingDateString + " at " + playingTimeString;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPlayingDateString() { return playingDateString; }
    public String getPlayingTimeString() { return playingTimeString; }
}
