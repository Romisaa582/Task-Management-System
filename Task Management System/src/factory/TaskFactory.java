/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;
import model.Task;
/**
 *
 * @author ascom
 */
public class TaskFactory {
    public static Task createTask(String type, String taskId, String description, String status, String deadline) {
        switch (type) {
            case "Bug":
                return new Task(taskId, description, status, deadline);
            case "Feature":
                return new Task(taskId, description, status, deadline);
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}
