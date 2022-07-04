/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rahman S
 * @since  / 1:03:11 PM
 */
public interface BaseModel {
    public void save();
    public void update();
    public void delete();

    public void fillFromResultSet(ResultSet result) throws SQLException;

}
