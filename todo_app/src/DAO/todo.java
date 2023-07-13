package DAO;

import Connection.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class todo {

    public static void insert(Task task) {
        try {
            PreparedStatement insertStatement = ConnectionProvider.getConnection().prepareStatement("INSERT INTO todo(title, description, deadline, priority, done) VALUES ( ?, ?, ?, ?, ?);");
            insertStatement.setString(1, task.title());
            insertStatement.setString(2, task.description());
            insertStatement.setTimestamp(3, task.deadline());
            insertStatement.setInt(4, task.priority());
            insertStatement.setBoolean(5, task.done());
            insertStatement.execute();
            insertStatement.close();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
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
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }

        return tasksList;
    }

    public static Task get(int id) {
        String query = "SELECT * FROM todo WHERE id=?;";
        String description = null;
        String name = null;
        Timestamp deadline = null;
        int priority = 0;
        Boolean done = null;
        try {
            PreparedStatement statement = ConnectionProvider
                    .getConnection()
                    .prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            statement.close();
            result.next();
            name = result.getString("title");
            description = result.getString("description");
            deadline = result.getTimestamp("deadline");
            priority = result.getInt("priority");
            done = result.getBoolean("done");
            result.close();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
        return new Task(id, name, description, deadline, priority, done);
    }

    public static void update(int id, Boolean done) {
        String query = "UPDATE todo SET done=? WHERE id=?;";
        try {
            PreparedStatement statement = ConnectionProvider
                    .getConnection()
                    .prepareStatement(query);
            statement.setBoolean(1, done);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
    }

    public static void remove(int id) {
        String query = "DELETE FROM todo WHERE id=?";
        try {
            PreparedStatement statement = ConnectionProvider
                    .getConnection()
                    .prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
    }

    public static List<Task> get(Boolean done) {
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
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
        return tasksList;
    }

    public static List<Task> getToday() {
        List<Task> tasksList = new ArrayList<>();
        String query = "DELETE FROM todo WHERE date_part(deadline)=current_date";
        try {
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
        } catch (SQLException e) {
            System.out.println("erreur au niveau de l'execution de la requête" +
                    "\nMessage: " + e.getMessage());
        }
        return tasksList;
    }
}
