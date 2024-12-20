package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import singleton.TaskManager;
import model.Task;
import factory.TaskFactory;
import prototype.PrototypeManager;
import proxy.TaskAccessProxy;
import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskManagementApp extends Application {

    private TaskManager taskManager = TaskManager.getInstance();
    private TextArea taskListArea = new TextArea(); // هذا هو مستطيل النص

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Management System");

        // Create components for the task form
        Label taskLabel = new Label("Task ID:");
        TextField taskIdField = new TextField();
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        Label statusLabel = new Label("Status:");
        TextField statusField = new TextField();
        Label deadlineLabel = new Label("Deadline:");
        TextField deadlineField = new TextField();
        Button addButton = new Button("Add Task");
        Button cloneButton = new Button("Clone Task");
        Button updateButton = new Button("Update Task");
        Button removeButton = new Button("Remove Task"); // Declare the remove button

        // Create a user (for access control)
        User user = new User("AdminUser", true);  // Example admin user

        // Create the Prototype Manager and a sample task
        Task prototypeTask = new Task("1", "Fix Bug", "In Progress", "2024-12-20");
        PrototypeManager prototypeManager = new PrototypeManager(prototypeTask);

        // Add Task Button Action
        addButton.setOnAction(e -> {
            String taskId = taskIdField.getText();
            String description = descriptionField.getText();
            String status = statusField.getText();
            String deadline = deadlineField.getText();
            Task task = TaskFactory.createTask("Bug", taskId, description, status, deadline);
            taskManager.addTask(task);
            taskListArea.appendText("Task Added: " + task.getDescription() + "\n");
        });

        // Clone Task Button Action
        cloneButton.setOnAction(e -> {
            try {
                Task clonedTask = prototypeManager.cloneTask();
                clonedTask.setTaskId(taskIdField.getText());
                taskListArea.appendText("Cloned Task ID: " + clonedTask.getTaskId() + "\n" +
                        "Description: " + clonedTask.getDescription() + "\n" +
                        "Status: " + clonedTask.getStatus() + "\n" +
                        "Deadline: " + clonedTask.getDeadline() + "\n");
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });

        // Update Task Button Action using Proxy for security
        updateButton.setOnAction(e -> {
            if (taskManager.getTasks().size() > 0) {
                Task selectedTask = taskManager.getTasks().get(0); // Assuming task[0] for demo
                TaskAccessProxy proxy = new TaskAccessProxy(selectedTask, user);
                proxy.updateTask("Completed");
                taskListArea.appendText("Task Updated: " + selectedTask.getDescription() + "\n");
            } else {
                taskListArea.appendText("No task available to update.\n");
            }
        });
        
        // Remove Task Button Action
        removeButton.setOnAction(e -> {
            String taskIdToRemove = taskIdField.getText();
            if (!taskIdToRemove.isEmpty()) {
                taskManager.removeTask(taskIdToRemove);
                taskListArea.appendText("Task Removed: " + taskIdToRemove + "\n");
            } else {
                taskListArea.appendText("Please enter a task ID to remove.\n");
            }
        });

        // Layout setup
        VBox layout = new VBox(10, taskLabel, taskIdField, descriptionLabel, descriptionField,
                statusLabel, statusField, deadlineLabel, deadlineField, addButton, cloneButton,
                updateButton, removeButton, new Label("Output:"), taskListArea); // Add label for Output
        Scene scene = new Scene(layout, 600, 600); // Adjust window size for better fit
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load tasks from the database when the application starts
        loadTasks();
    }

    private void loadTasks() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM `table`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String taskId = resultSet.getString("Task_ID");
                String description = resultSet.getString("Description");
                String status = resultSet.getString("Status");
                String deadline = resultSet.getString("Deadline");

                // Display the task in the TextArea
                taskListArea.appendText("Task ID: " + taskId + ", Description: " + description +
                                        ", Status: " + status + ", Deadline: " + deadline + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            taskListArea.appendText("Error loading tasks from database.\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
