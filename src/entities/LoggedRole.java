package entities;

import View.MainTheme;

public class LoggedRole {

    static String loggedRole;

    public LoggedRole() {
    }

    public static String getLoggedRole() {
        return loggedRole;
    }

    public static void setLoggedRole(String loggedRole) {
        LoggedRole.loggedRole = loggedRole;
    }
}
