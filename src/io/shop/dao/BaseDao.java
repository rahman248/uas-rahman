/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.shop.dao;

import io.shop.connection.DbConnection;
import io.shop.model.BaseModel;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Rahman S
 * @since / 1:09:47 PM
 */
public class BaseDao {

    protected Connection connection;
    protected String table;
    protected String primaryKey;

    protected BaseDao() {
        connection = DbConnection.createConnection();
    }

    public <T extends BaseModel> ArrayList<T> all(Class<T> modelClass) {
        ArrayList<T> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM " + table);

            while (result.next()) {
                T model = modelClass.getDeclaredConstructor().newInstance();

                model.fillFromResultSet(result);

                list.add(model);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public <T extends BaseModel> ArrayList<T> all(Class<T> modelClass, String orderBy) {
        ArrayList<T> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + table + " ORDER BY " + orderBy);

            while (result.next()) {
                T model = modelClass.getDeclaredConstructor().newInstance();

                model.fillFromResultSet(result);

                list.add(model);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public <T extends BaseModel> T find(int id, Class<T> modelClass) {
        T model = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + primaryKey + " = ?");

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                model = modelClass.getDeclaredConstructor().newInstance();

                model.fillFromResultSet(rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }

    public <T extends BaseModel> ArrayList<T> where(String where, Class<T> modelClass) {
        ArrayList<T> results = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + where);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                T model = modelClass.getDeclaredConstructor().newInstance();
                model.fillFromResultSet(rs);

                results.add(model);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public <T extends BaseModel> ArrayList<T> where(String where, String orderBy, Class<T> modelClass) {
        ArrayList<T> results = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + where + " ORDER BY " + orderBy);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                T model = modelClass.getDeclaredConstructor().newInstance();

                model.fillFromResultSet(rs);

                results.add(model);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public <T extends BaseModel> T whereOne(String where, Class<T> modelClass) {
        T results = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + where);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                results = modelClass.getDeclaredConstructor().newInstance();

                results.fillFromResultSet(rs);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException
                | InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

    public String add(Map<String, Object> data) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        columns.append("(");
        values.append("(");

        columns.append(String.join(",", data.keySet()));

        String formatedValues = data.entrySet().stream().map((t) -> {

            if (t.getKey().equals("create_date")) {
                return t.getValue().toString().replace("\"", "");
            } else {
                return "\"" + t.getValue() + "\"";
            }

        }).collect(Collectors.joining(", "));

        values.append(formatedValues);

        columns.append(")");
        values.append(")");

        String query = "INSERT INTO " + table + columns + " VALUES " + values;
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void update(Map<String, Object> data, int id) {
        String query = "UPDATE " + table + " SET ";
        System.out.println("Data " + id);
        String formatedValues = data.entrySet().stream().map((item) -> {
            return item.getKey() + "=" + "'" + item.getValue() + "'";

        }).collect(Collectors.joining(", "));

        query += formatedValues + " WHERE " + primaryKey + "=" + String.valueOf(id);
        System.out.println("Data " + query);

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + table + " WHERE " + primaryKey + "=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteWhere(String where) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM " + table + " WHERE " + where);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet print() {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM " + table);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

}
