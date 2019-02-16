/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Win
 */
public class SqlUI implements Serializable {

    static String userName, password, serverName;

    public SqlUI() {
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        SqlUI.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SqlUI.password = password;
    }

    public static String getServerName() {
        return serverName;
    }

    public static void setServerName(String serverName) {
        SqlUI.serverName = serverName;
    }
}
