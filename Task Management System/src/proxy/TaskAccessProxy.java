/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;
import model.Task;
import singleton.NotificationSystem;
import model.User;
/**
 *
 * @author ascom
 */
public class TaskAccessProxy {
    private Task task;
    private User user;

    public TaskAccessProxy(Task task, User user) {
        this.task = task;
        this.user = user;
    }

    public void updateTask(String newStatus) {
        if (user.isAdmin()) {
            task.setStatus(newStatus);
            NotificationSystem.getInstance().sendNotification("Task updated: " + task.getDescription());
        } else {
            System.out.println("Access Denied: You do not have permission to modify this task.");
        }
    }
}
