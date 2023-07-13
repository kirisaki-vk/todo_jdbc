import Prompt.PromptScanner;
import Prompt.UI;
import DAO.Task;
import DAO.todo;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        while (true) {
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
                    UI.showTasks(todo.get(true));
                    break;
                case 5:
                    UI.showTasks(todo.get(false));
                    break;
                case 6:
                    UI.showTasks(todo.getToday());
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }
}