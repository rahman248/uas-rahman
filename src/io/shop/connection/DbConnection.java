/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.shop.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rahman S
 * @since  / 12:48:33 PM
 */
public class DbConnection {
    static Connection con;

    public static Connection createConnection() {

        if (con == null) {

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mariadb://localhost:3307/shop", "root", "KopikoP1!@#$");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

        return con;
    }

}
