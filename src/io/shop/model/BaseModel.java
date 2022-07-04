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
    public void updateData(int id);
    public void delete();

    public void fillFromResultSet(ResultSet result) throws SQLException;

}
