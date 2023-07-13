package DAO;

import Connection.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class todo {

    public static void insert(Task task) throws SQLException {
        PreparedStatement insertStatement = ConnectionProvider.getConnection().prepareStatement("INSERT INTO todo(title, description, deadline, priority, done) VALUES ( ?, ?, ?, ?, ?);");
        insertStatement.setString(1, task.title());
        insertStatement.setString(2, task.description());
        insertStatement.setTimestamp(3, task.deadline());
        insertStatement.setInt(4, task.priority());
        insertStatement.setBoolean(5, task.done());
        insertStatement.execute();
        insertStatement.close();
    }

    public static List<Task> getAll() {
        List<Task> tasksList = new ArrayList<>();
        String query = "SELECT * FROM todo ORDER BY priority;";
        try {
            ResultSet result = ConnectionProvider.getConnection().createStatement().executeQuery(query);
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("title");
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
        String query = "SELECT * FROM todo WHERE id=?;";
        PreparedStatement statement = ConnectionProvider
                .getConnection()
                .prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        statement.close();
        result.next();
        String name = result.getString("title");
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
        String name = result.getString("title");
        String description = result.getString("description");
        Timestamp deadline = result.getTimestamp("deadline");
        int priority = result.getInt("priority");
        Boolean done = result.getBoolean("done");
        return new Task(id, name, description, deadline, priority, done);
    }

    public static List<Task> get(Boolean done) throws SQLException {
        List<Task> tasksList = new ArrayList<>();
        String query = "SELECT * FROM todo WHERE done="+done+";";
        try {
            ResultSet result = ConnectionProvider.getConnection().createStatement().executeQuery(query);
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("title");
                String description = result.getString("description");
                Timestamp deadline = result.getTimestamp("deadline");
                int priority = result.getInt("priority");
                Boolean isdone = result.getBoolean("done");
                tasksList.add(new Task(id, name, description, deadline, priority, isdone));
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasksList;
    }

    public static List<Task> getToday() throws SQLException {
        List<Task> tasksList = new ArrayList<>();
        String query = "DELETE FROM todo WHERE date_part(deadline)=current_date";
        PreparedStatement statement = ConnectionProvider
                .getConnection()
                .prepareStatement(query);
        ResultSet result = statement.executeQuery();
        statement.close();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("title");
            String description = result.getString("description");
            Timestamp deadline = result.getTimestamp("deadline");
            int priority = result.getInt("priority");
            Boolean done = result.getBoolean("done");
            tasksList.add(new Task(id, name, description, deadline, priority, done));
        }
        result.close();
        return tasksList;
    }
}
