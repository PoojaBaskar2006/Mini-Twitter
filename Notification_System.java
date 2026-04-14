import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Notification_System {
    // Map of Username to a List of Messages
    private Map<String, List<String>> notifications = new HashMap<>();

    public void sendNotification(String username, String message) {
        if (!notifications.containsKey(username)) {
            notifications.put(username, new ArrayList<>());
        }
        notifications.get(username).add(message);
    }

    public void viewNotifications(User user) {
        String username = user.getUsername();
        
        if (notifications.containsKey(username) && !notifications.get(username).isEmpty()) {
            List<String> messages = notifications.get(username);
            System.out.println("\n--- All Notifications for @" + username + " ---");
            for (String msg : messages) {
                System.out.println(msg);
            }
            System.out.println("-----------------------------------");
            // I removed messages.clear() here so they don't get deleted!
        } else {
            System.out.println("No notifications yet.");
        }
    }
}