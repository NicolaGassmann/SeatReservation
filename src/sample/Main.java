package sample;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Game> allGames = SeatReservationRepository.getGames();
    private static List<Guest> allGuests = SeatReservationRepository.getGuests();
    private static List<Team> allTeams = SeatReservationRepository.getTeams();

    public static void main(String[] args) {
        for (int i = 0; i == 0; ) {
            System.out.println("Please select:");
            System.out.println("\t1) Show all games");
            System.out.println("\t2) Show all guests");
            System.out.println("\t3) Show all seats");
            System.out.println("\t4) Show all teams");
            System.out.println("\t5) Reserve a seat");
            System.out.println("\t6) Add a team");
            System.out.println("\t7) Add a game");
            System.out.println("\t8) Exit");

            try {
                Scanner s = new Scanner(System.in);
                System.out.println("-------------------------");
                switch (s.nextInt()) {
                    case 1:
                        showGames();
                        break;

                    case 2:
                        showGuests();
                        break;

                    case 3:
                        showSeats();
                        break;

                    case 4:
                        showTeams();
                        break;

                    case 5:
                        reserveSeat();
                        break;

                    case 6:
                        addTeam();
                        break;

                    case 7:
                        addGame();
                        break;

                    case 8:
                        i = 1;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1 and 8");
            }
        }
    }

    private static void showGames() {
        Scanner s = new Scanner(System.in);
        List<Game> pastGames = new ArrayList<>();
        List<Game> futureGames = new ArrayList<>();
        for (Game game : allGames) {
            String gameDate = game.getPlayingDateString() + " " + game.getPlayingTimeString();
            LocalDateTime localDateTime = LocalDateTime.parse(gameDate,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm") );
            long millis = localDateTime
                    .atZone(ZoneId.systemDefault())
                    .toInstant().toEpochMilli();
            if(millis <= System.currentTimeMillis()){
                pastGames.add(game);
            }else{
                futureGames.add(game);
            }
        }

        System.out.println("Do you want all past or all future games (P/F):");
        String answer = s.next();
        if(answer.equals("P") || answer.equals("p")){
            for(Game game:pastGames){
                System.out.println(game.outputString());
            }
        }else if(answer.equals("F") || answer.equals("f")){
            for(Game game:futureGames){
                System.out.println(game.outputString());
            }
        }else{
            System.out.println("wrong answer");
        }
    }

    private static void showGuests() {
        for (Guest guest : allGuests) {
            System.out.println(guest.outputString());
        }
    }

    private static void showSeats() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a game Id: ");
        int gameId = s.nextInt();
        Game selectedGame = null;
        for (Game game : allGames) {
            if (game.getGameId() == gameId) {
                selectedGame = game;
            }
        }
        if (selectedGame != null) {
            List<Seat> reservedSeats = SeatReservationRepository.getReservedSeats(selectedGame);
            List<Seat> availableSeats = SeatReservationRepository.getAvailableSeats(selectedGame);
            System.out.println("Reserved Seats:");
            for (Seat seat : reservedSeats) {
                System.out.println(seat.outputString());
            }
            System.out.println("\nAvailable Seats:");
            for (Seat seat : availableSeats) {
                System.out.println(seat.outputString());
            }
        } else {
            System.out.println("No game found!");
        }
    }

    private static void showTeams() {
        for (Team team : allTeams) {
            System.out.println(team.outputString());
        }
    }

    private static void reserveSeat() {
        try {
            int x = 0;
            Scanner s = new Scanner(System.in);
            while (x == 0) {
                boolean objectNotFound = true;
                Guest selectedGuest = null;
                while (objectNotFound) {
                    System.out.println("Please enter your guestId:");
                    int guestId = s.nextInt();
                    for (Guest guest : allGuests) {
                        if (guest.getGuestId() == guestId) {
                            selectedGuest = guest;
                            objectNotFound = false;
                        }
                    }
                    if (selectedGuest == null) {
                        System.out.println("No guest found!\nDo you want to enter another guest id? (Y/N): ");
                        String answer = s.next();
                        if (answer.equals("N") || answer.equals("n")) {
                            x = 1;
                            break;
                        }
                    }
                }
                Game selectedGame = null;
                if (x == 0) {
                    objectNotFound = true;
                    while (objectNotFound) {
                        System.out.println("Please enter the game id of the game in which you want to reserve a seat:");
                        int gameId = s.nextInt();
                        for (Game game : allGames) {
                            if (game.getGameId() == gameId) {
                                selectedGame = game;
                                objectNotFound = false;
                            }
                        }
                        if (selectedGame == null) {
                            System.out.println("No game found!\nDo you want to enter another game id? (Y/N): ");
                            String answer = s.next();
                            if (answer.equals("N") || answer.equals("n")) {
                                x = 1;
                                break;
                            }
                        }
                    }
                }
                Seat selectedSeat = null;
                if (x == 0) {
                    objectNotFound = true;
                    while (objectNotFound) {
                        System.out.println("Please enter a the seat number of the seat you wish to reserve:");
                        int seatNumber = s.nextInt();
                        List<Seat> allSeats = new ArrayList<>(SeatReservationRepository.getReservedSeats(selectedGame));
                        allSeats.addAll(SeatReservationRepository.getAvailableSeats(selectedGame));
                        for (Seat seat : allSeats) {
                            if (seat.getSeatNumber() == seatNumber) {
                                selectedSeat = seat;
                                objectNotFound = false;
                            }
                        }
                        if (selectedSeat == null) {
                            System.out.println("No seat found!\nDo you want to enter another seat number? (Y/N): ");
                            String answer = s.next();
                            if (answer.equals("N") || answer.equals("n")) {
                                x = 1;
                                break;
                            }
                        }
                    }
                }
                boolean reservationSuccessful = SeatReservationRepository.addReservation(selectedGuest, selectedSeat, selectedGame);
                if (reservationSuccessful) {
                    System.out.println("Reservation completed successfully");
                } else {
                    System.out.println("Reservation failed: Seat already taken");
                }
                System.out.println("Return to menu? (Y/N): ");
                String exitAnswer = s.next();
                if (exitAnswer.equals("Y") || exitAnswer.equals("y")) {
                    x = 1;
                } else {
                    x = 0;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Only input numbers for ids and numbers!");
        }
    }

    private static void addTeam() {
        Scanner s = new Scanner(System.in);
        int x = 0;
        while (x == 0) {
            System.out.println("Please enter the Team name: ");
            String teamName = s.next();
            if (teamAlreadyExists(teamName)) {
                System.out.println("Team " + teamName + " already exists");
            } else {
                boolean addSuccessful = SeatReservationRepository.addTeam(teamName);
                if (addSuccessful) {
                    System.out.println("Team successfully added");
                } else {
                    System.out.println("Adding failed");
                }
            }
            System.out.println("Return to menu? (Y/N): ");
            String exitAnswer = s.next();
            if (exitAnswer.equals("Y") || exitAnswer.equals("y")) {
                x = 1;
            } else {
                x = 0;
            }
        }
    }

    private static void addGame() {
        try {
            Scanner s = new Scanner(System.in);
            int x = 0;
            while (x == 0) {
                boolean objectFound = false;
                int teamAId = 0;
                int teamBId = 0;
                String datetime;
                while (!objectFound) {
                    System.out.println("Please enter the id of team A: ");
                    teamAId = s.nextInt();
                    if (teamDoesNotExist(teamAId)) {
                        System.out.println("This team does not exist!");
                        System.out.println("Do you want to enter another team id (Y/N): ");
                        String answer = s.next();
                        if (answer.equals("N") || answer.equals("n")) {
                            x = 1;
                            break;
                        }
                    } else {
                        objectFound = true;
                    }
                }
                objectFound = false;
                if (x == 0) {
                    while (!objectFound) {
                        System.out.println("Please enter the id of team B: ");
                        teamBId = s.nextInt();
                        if (teamDoesNotExist(teamBId)) {
                            System.out.println("This team does not exist!");
                            System.out.println("Do you want to enter another team id (Y/N): ");
                            String answer = s.next();
                            if (answer.equals("N") || answer.equals("n")) {
                                x = 1;
                                break;
                            }
                        } else {
                            objectFound = true;
                        }
                    }
                }
                if (x == 0) {
                    System.out.println("Please enter the date of the game (YYYY-MM-DD): ");
                    String date = s.next();
                    System.out.println("Please enter the time when the game starts (hh:mm): ");
                    String time = s.next();
                    time = time + ":00";
                    datetime = date + " " + time;

                    boolean addSuccessful = SeatReservationRepository.addGame(teamAId, teamBId, datetime);
                    if (addSuccessful) {
                        System.out.println("Game added successfully");
                    } else {
                        System.out.println("Adding game failed! Please check your team ids and the format of your date and time");
                    }
                }
                System.out.println("Return to menu? (Y/N): ");
                String exitAnswer = s.next();
                if (exitAnswer.equals("Y") || exitAnswer.equals("y")) {
                    x = 1;
                } else {
                    x = 0;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Only input numbers for ids!");
        }
    }

    private static boolean teamAlreadyExists(String teamName) {
        boolean alreadyExists = false;
        for (Team team : allTeams) {
            if (team.getName().equals(teamName)) {
                alreadyExists = true;
            }
        }
        return alreadyExists;
    }

    private static boolean teamDoesNotExist(int teamId) {
        boolean doesNotExist = true;
        for (Team team : allTeams) {
            if (team.getTeamId() == teamId) {
                doesNotExist = false;
            }
        }
        return doesNotExist;
    }
}