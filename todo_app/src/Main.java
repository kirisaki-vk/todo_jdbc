import Prompt.PromptScanner;
import Prompt.UI;
import DAO.Task;
import DAO.todo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        while (true) {
            PromptScanner.clear();
            UI.showMenu();
            int choice = PromptScanner.get().nextInt();
            switch (choice) {
                case 1:
                    Task newTask = UI.addTask();
                    todo.insert(newTask);
                    break;

                case 2:
                    UI.removeTask();
                    break;

                case 3:
                    UI.updateTask();
                    break;
                case 4:
                    UI.showTasks(todo.getAll());
                    System.out.println("Press enter to go to the menu");
                    PromptScanner.get().nextLine();
                    PromptScanner.get().nextLine();
                    break;

                case 5:
                    UI.showTasks(todo.get(true));
                    System.out.println("Press enter to go to the menu");
                    PromptScanner.get().nextLine();
                    PromptScanner.get().nextLine();
                    break;
                case 6:
                    UI.showTasks(todo.get(false));
                    System.out.println("Press enter to go to the menu");
                    PromptScanner.get().nextLine();
                    PromptScanner.get().nextLine();
                    break;
                case 7:
                    UI.showTasks(todo.getToday());
                    System.out.println("Press enter to go to the menu");
                    PromptScanner.get().nextLine();
                    PromptScanner.get().nextLine();
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}