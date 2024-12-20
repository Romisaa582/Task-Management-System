/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author ascom
 */
public class NotificationSystem {
    private static NotificationSystem instance;

    private NotificationSystem() {}

    public static NotificationSystem getInstance() {
        if (instance == null) {
            instance = new NotificationSystem();
        }
        return instance;
    }

    public void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}
