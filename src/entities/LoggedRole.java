package entities;

public class LoggedRole {

    static String loggedRole, username;

    public LoggedRole() {
    }

    public static String getLoggedRole() {
        return loggedRole;
    }

    public static void setLoggedRole(String loggedRole) {
        LoggedRole.loggedRole = loggedRole;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoggedRole.username = username;
    }
}
