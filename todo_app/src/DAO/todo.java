package DAO;

import Connection.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class todo {

    public static void insert(String title, String description, Date deadline, int priority, Boolean done) throws SQLException {
        PreparedStatement insertStatement = ConnectionProvider.getConnection().prepareStatement("INSERT INTO todo(title, description, deadline, priority, done) VALUES ( ?, ?, ?, ?, ?);");
        insertStatement.setString(1, title);
        insertStatement.setString(2, description);
        insertStatement.setDate(3, deadline);
        insertStatement.setInt(4, priority);
        insertStatement.setBoolean(5, done);
        insertStatement.execute();
        insertStatement.close();
    }

    public static List<Task> getAll() {
        List<Task> tasksList = new ArrayList<>();
        String query = "SELECT * FROM todo_app ORDER BY id;";
        try {
            ResultSet result = ConnectionProvider.getConnection().createStatement().executeQuery(query);
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                Timestamp deadline = result.getTimestamp("deadline");
                int priority = result.getInt("priority");
                Boolean done = result.getBoolean("done");
                tasksList.add(new Task(id, name, description, deadline, priority, done));
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasksList;
    }

    public static Task get(int id) throws SQLException {
        String query = "SELECT * FROM todo_app WHERE id=?;";
        PreparedStatement statement = ConnectionProvider
                .getConnection()
                .prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        statement.close();
        result.next();
        String name = result.getString("name");
        String description = result.getString("description");
        Timestamp deadline = result.getTimestamp("deadline");
        int priority = result.getInt("priority");
        Boolean done = result.getBoolean("done");
        result.close();
        return new Task(id, name, description, deadline, priority, done);
    }

    public static int update(int id, Boolean done) throws SQLException {
        String query = "UPDATE todo SET done=? WHERE id=?;";
        PreparedStatement statement = ConnectionProvider
                .getConnection()
                .prepareStatement(query);
        statement.setBoolean(1, done);
        statement.setInt(2, id);
        return statement.executeUpdate();
    }

    public static Task remove(int id) throws SQLException {
        String query = "DELETE FROM todo WHERE id=? RETURNING *";
        PreparedStatement statement = ConnectionProvider
                .getConnection()
                .prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        statement.close();
        result.next();
        String name = result.getString("name");
        String description = result.getString("description");
        Timestamp deadline = result.getTimestamp("deadline");
        int priority = result.getInt("priority");
        Boolean done = result.getBoolean("done");
        return new Task(id, name, description, deadline, priority, done);
    }

    public static List<Task> getDone() throws SQLException {
        List<Task> tasksList = new ArrayList<>();
        String query = "SELECT * FROM todo_app WHERE done=t;";
        try {
            ResultSet result = ConnectionProvider.getConnection().createStatement().executeQuery(query);
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                Timestamp deadline = result.getTimestamp("deadline");
                int priority = result.getInt("priority");
                Boolean done = result.getBoolean("done");
                tasksList.add(new Task(id, name, description, deadline, priority, done));
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasksList;
    }
}
