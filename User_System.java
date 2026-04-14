import java.util.HashMap;
import java.util.Map;

class User {
    private String username;
    private String password;
    private String bio; // Added bio field

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bio = "Hey there! I'm using Mini Twitter."; // Default bio
    }

    public String getUsername() { return username; }
    public boolean checkPassword(String pass) { return this.password.equals(pass); }
    
    // Getters and Setters for Bio
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}   

class User_System {
    private Map<String, User> users = new HashMap<>();

    public void register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    // UPDATED: Overloaded Method 1 (My Profile)
    // Now accepts Tweet_System to count tweets
    public void viewProfile(User user, Tweet_System tweetSystem) {
        System.out.println("\n=== My Profile ===");
        System.out.println("Username: @" + user.getUsername());
        System.out.println("Bio: " + user.getBio());
        System.out.println("Tweets Posted: " + tweetSystem.getTweetCount(user.getUsername()));
        System.out.println("==================");
    }

    // UPDATED: Overloaded Method 2 (Other Profile)
    // Now accepts Tweet_System to count tweets
    public void viewProfile(User currentUser, String targetUsername, Tweet_System tweetSystem) {
        if (users.containsKey(targetUsername)) {
            User target = users.get(targetUsername);
            System.out.println("\n=== " + target.getUsername() + "'s Profile ===");
            System.out.println("Bio: " + target.getBio());
            System.out.println("Tweets Posted: " + tweetSystem.getTweetCount(target.getUsername()));
            System.out.println("==================");
        } else {
            System.out.println("User not found.");
        }
    }
}