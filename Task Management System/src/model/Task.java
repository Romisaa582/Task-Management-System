/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ascom
 */
public class Task implements Cloneable {
    private String taskId;
    private String description;
    private String status;
    private String deadline;

    // Constructor
    public Task(String taskId, String description, String status, String deadline) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    // Getters and setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}