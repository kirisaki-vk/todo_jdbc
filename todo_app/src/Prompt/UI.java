package Prompt;

import DAO.Task;

import java.sql.Timestamp;
import java.util.List;

public class UI {
    public static void showMenu() {
        System.out.println("=======ToDo App=======");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. Update Task");
        System.out.println("4. Show done Task(s)");
        System.out.println("5. Show undone Task(s)");
        System.out.println("6. Show today's Task(s)");
        System.out.println("7. Quit");
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

    public static Task addTask() {
        System.out.println("=======Add Task=======");
        System.out.println("ID: ");
        int id = PromptScanner.get().nextInt();
        System.out.println("Title: ");
        String title = PromptScanner.get().next();
        System.out.println("Description: ");
        String description = PromptScanner.get().next();
        System.out.println("Deadline: ");
        Timestamp deadline = Timestamp.valueOf(PromptScanner.get().next());
        System.out.println("Priority: ");
        int priority = PromptScanner.get().nextInt();
        return new Task(id, title, description, deadline, priority, false);
    }

}
