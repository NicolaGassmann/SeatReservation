package sample;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SeatReservationRepository {
    private final static String dbUrl = "jdbc:mysql://localhost/seatreservation?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String username = "root";
    private final static String password = "";

    public static List<Team> getTeams() {
        List<Team> allTeams = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM team";
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int teamId = resultSet.getInt("TeamId");
                String name = resultSet.getString("Name");
                allTeams.add(new Team(teamId, name));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allTeams;
    }

    public static List<Game> getGames() {
        List<Game> allGames = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM game";
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet != null) {
                int gameNumber = 1;
                while (resultSet.next()) {
                    int gameId = resultSet.getInt("GameId");
                    List<Team> allTeams = getTeams();
                    Team teamA = null;
                    int teamAId = resultSet.getInt("TeamAId");
                    for (Team team : allTeams) {
                        if (team.getTeamId() == teamAId) {
                            teamA = team;
                        }
                    }
                    Team teamB = null;
                    int teamBId = resultSet.getInt("TeamBId");
                    for (Team team : allTeams) {
                        if (team.getTeamId() == teamBId) {
                            teamB = team;
                        }
                    }
                    Date playingDate = resultSet.getDate("PlayingDate");
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    String playingDateString = dateFormat.format(playingDate);
                    Time playingTime = resultSet.getTime("PlayingDate");
                    String pls = playingTime.toString();
                    String playingTimeString = pls.replace(":00", "");
                    if (playingTimeString.length() < 5) {
                        playingTimeString = playingTimeString + ":00";
                    }
                    allGames.add(new Game(gameNumber, gameId, teamA, teamB, playingDate, playingDateString, playingTime, playingTimeString));
                    gameNumber++;
                }
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allGames;
    }

    public static List<Guest> getGuests() {
        List<Guest> allGuests = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String queryGuest = "SELECT * FROM guest";
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryGuest);
            while (resultSet.next()) {
                int guestId = resultSet.getInt("GuestId");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String streetAndNumber = resultSet.getString("StreetAndNumber");
                String zip = resultSet.getString("Zip");
                String domicile = resultSet.getString("Domicile");
                String country = resultSet.getString("Country");
                String phone = resultSet.getString("Phone");
                String mobile = resultSet.getString("Mobile");
                String email = resultSet.getString("Email");
                allGuests.add(new Guest(guestId, firstName, lastName, streetAndNumber, zip, domicile, country, phone, mobile, email));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allGuests;
    }

    public static List<Category> getCategories() {
        List<Category> allCategories = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM category";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("CategoryId");
                String name = resultSet.getString("Name");
                double price = resultSet.getDouble("Price");
                allCategories.add(new Category(categoryId, name, price));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    public static List<Seat> getReservedSeats(Game game) {
        List<Seat> reservedSeats = new ArrayList<>();
        String queryReservedSeat = "SELECT S.SeatId SeatId, S.SeatRow SeatRow, S.SeatNumber SeatNumber, S.CategoryId CategoryId FROM reservation R JOIN seat S ON R.SeatId = S.SeatId WHERE GameId = " + game.getGameId();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(queryReservedSeat);
            while (resultSet.next()) {
                int seatId = resultSet.getInt("SeatId");
                int seatNumber = resultSet.getInt("SeatNumber");
                Category category = findCategory(resultSet.getInt("CategoryId"));
                int seatRow = resultSet.getInt("SeatRow");
                reservedSeats.add(new Seat(seatId, game, seatNumber, category, seatRow, true));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return reservedSeats;
    }

    public static List<Seat> getAvailableSeats(Game game) {
        List<Seat> availableSeats = new ArrayList<>();
        List<Seat> allSeats = new ArrayList<>();
        List<Seat> reservedSeats = getReservedSeats(game);
        String query = "SELECT * FROM seat";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                int seatId = resultSet.getInt("SeatId");
                int seatNumber = resultSet.getInt("SeatNumber");
                Category category = findCategory(resultSet.getInt("CategoryId"));
                int seatRow = resultSet.getInt("SeatRow");
                Seat seat = new Seat(seatId, game, seatNumber, category, seatRow, false);
                allSeats.add(seat);
                availableSeats.add(seat);
            }
            for (Seat seat : allSeats) {
                for (Seat reservedSeat : reservedSeats) {
                    if (seat.getSeatId() == reservedSeat.getSeatId()) {
                        availableSeats.remove(seat);
                    }
                }
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return availableSeats;
    }

    public static Category findCategory(int id) {
        List<Category> allCategories = getCategories();
        Category foundCategory = null;
        for (Category category : allCategories) {
            if (category.getCategoryId() == id) {
                foundCategory = category;
            }
        }
        return foundCategory;
    }

    public static boolean addReservation(Guest guest, Seat seat, Game game) {
        boolean successful = false;
        if (!seatTaken(seat, game)) {
            String query = "INSERT INTO reservation(GuestId, SeatId, GameId) Values (?, ?, ?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                PreparedStatement prepStatement = conn.prepareStatement(query);
                prepStatement.setInt(1, guest.getGuestId());
                prepStatement.setInt(2, seat.getSeatId());
                prepStatement.setInt(3, game.getGameId());
                int affectedRows = prepStatement.executeUpdate();
                if (affectedRows > 0) {
                    successful = true;
                }
                prepStatement.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return successful;
    }

    private static boolean seatTaken(Seat seat, Game game) {
        boolean isTaken = false;
        List<Seat> reservedSeats = getReservedSeats(game);
        for (Seat reservedSeat : reservedSeats) {
            if (seat.getSeatId() == reservedSeat.getSeatId()) {
                isTaken = true;
            }
        }
        return isTaken;
    }

    public static boolean addTeam(String teamName) {
        boolean successful = false;
        String query = "INSERT INTO team(Name) Values (?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, teamName);
            int affectedRows = prepStatement.executeUpdate();
            if (affectedRows > 0) {
                successful = true;
            }
            prepStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return successful;
    }

    public static boolean addGame(int teamAId, int teamBId, String datetime){
        boolean successful = false;
        String query = "INSERT INTO game(TeamAId, TeamBId, PlayingDate) Values (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, teamAId);
            prepStatement.setInt(2, teamBId);
            prepStatement.setString(3, datetime);
            int affectedRows = prepStatement.executeUpdate();
            if (affectedRows > 0) {
                successful = true;
            }
            prepStatement.close();
        } catch (ClassNotFoundException | SQLException e) {
            successful = false;
        }
        return successful;
    }
}