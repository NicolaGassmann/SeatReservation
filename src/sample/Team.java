package sample;

public class Team {
    int teamId;
    String name;

    public Team(int teamId, String name){
        this.teamId = teamId;
        this.name = name;
    }

    public String outputString(){
        return "Team " + name + " with id " + teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }
}
