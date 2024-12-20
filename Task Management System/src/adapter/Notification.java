/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author ascom
 */
public interface Notification {
    void sendNotification(String message);
}

class EmailNotification implements Notification {
    public void sendNotification(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSNotification implements Notification {
    public void sendNotification(String message) {
        System.out.println("SMS: " + message);
    }
}

class NotificationAdapter {
    private Notification notification;

    public NotificationAdapter(String type) {
        if (type.equals("Email")) {
            notification = new EmailNotification();
        } else if (type.equals("SMS")) {
            notification = new SMSNotification();
        }
    }

    public void sendNotification(String message) {
        notification.sendNotification(message);
    }
}
