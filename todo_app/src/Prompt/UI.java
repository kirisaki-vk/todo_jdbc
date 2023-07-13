package Prompt;

import DAO.Task;
import DAO.todo;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class UI {
    public static void showMenu() {
        System.out.println("=======ToDo App=======");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. Update Task");
        System.out.println("4. Show all Tasks");
        System.out.println("5. Show done Task(s)");
        System.out.println("6. Show undone Task(s)");
        System.out.println("7. Show today's Task(s)");
        System.out.println("8. Quit");
    }

    public static void showTasks(List<Task> tasks) {
        tasks.forEach(task -> {
            System.out.println("=====================");
            System.out.println("ID: " + task.id());
            System.out.println("Name: " + task.title());
            System.out.println("Description: " + task.description());
            System.out.println("Deadline: " + task.deadline());
            System.out.println("Priority: " + task.priority());
            System.out.println("Done: " + task.done());
            System.out.println("=====================");
        });
    }

    public static void showTask(Task task) {
        System.out.println("=====================");
        System.out.println("ID: " + task.id());
        System.out.println("Name: " + task.title());
        System.out.println("Description: " + task.description());
        System.out.println("Deadline: " + task.deadline());
        System.out.println("Priority: " + task.priority());
        System.out.println("Done: " + task.done());
        System.out.println("=====================");
    }

    public static Task addTask() throws InterruptedException {
        System.out.println("=======Add Task=======");
        System.out.println("Title: ");
        PromptScanner.get().nextLine();
        String title = PromptScanner.get().nextLine();
        System.out.println("Description: ");
        String description = PromptScanner.get().nextLine();
        System.out.println("Deadline:(Timestamp format must be yyyy-mm-dd hh:mm:ss) ");
        Timestamp deadline = Timestamp.valueOf(PromptScanner.get().nextLine());
        System.out.println("Priority: ");
        int priority = PromptScanner.get().nextInt();
        return new Task(0, title, description, deadline, priority, false);
    }

    public static void removeTask() throws SQLException {
        int choosedId = PromptScanner.get().nextInt();
        todo.remove(choosedId);
        System.out.println("Removed Task Id: " + choosedId);
    }

    public static void updateTask() throws SQLException {
        System.out.println("Choose the task id to be modified: ");
        int choosedId = PromptScanner.get().nextInt();
        System.out.println("Choose the task state(empty for not done, 'true' if done): ");
        Boolean newState = PromptScanner.get().hasNext();
        todo.update(choosedId, newState);
    }
}
