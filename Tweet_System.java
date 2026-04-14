import java.util.HashMap;
import java.util.Map;

// Interface for Polymorphism
interface Interactable {
    void displayTweet();
}

// Simple Base Class (No abstract)
class Post {
    private String id;
    private User author;

    public Post(String id, User author) {
        this.id = id;
        this.author = author;
    }

    public String getId() { return id; }
    public User getAuthor() { return author; }
}

// Single Inheritance
class Tweet extends Post implements Interactable {
    private String text;
    private int likesCount;
    private int commentsCount;

    public Tweet(String id, User author, String text) {
        super(id, author);
        this.text = text;
        this.likesCount = 0;
        this.commentsCount = 0;
    }

    public void addLike() { likesCount++; }
    public void removeLike() { likesCount--; }
    public void addCommentCount() { commentsCount++; }
    public void removeCommentCount() { commentsCount--; }

    public String getText() { return text; }

    // Polymorphism: Implementing interface method
    @Override
    public void displayTweet() {
        System.out.println("Tweet ID: " + getId() + " | Author: " + getAuthor().getUsername());
        System.out.println("Content: " + text);
        System.out.println("Likes: " + likesCount + " | Comments: " + commentsCount);
        System.out.println("-------------------------");
    }
}

class Tweet_System {
    private Map<String, Tweet> tweets = new HashMap<>();
    private int tweetCounter = 1;

    public Tweet createTweet(User author, String text) {
        String tweetId = "T" + tweetCounter;
        tweetCounter++;
        Tweet newTweet = new Tweet(tweetId, author, text);
        tweets.put(tweetId, newTweet);
        System.out.println("Tweet posted with ID: " + tweetId);
        return newTweet;
    }

    public Tweet getTweet(String id) {
        return tweets.get(id);
    }

    public void deleteTweet(String id, User currentUser) {
        Tweet tweet = tweets.get(id);
        if (tweet != null && tweet.getAuthor().getUsername().equals(currentUser.getUsername())) {
            tweets.remove(id);
            System.out.println("Tweet deleted.");
        } else {
            System.out.println("Cannot delete this tweet.");
        }
    }

    public Map<String, Tweet> getAllTweets() {
        return tweets;
    }

    // ==========================================
    // NEW METHOD ADDED HERE
    // This allows the User Profile to count tweets
    // ==========================================
    public int getTweetCount(String username) {
        int count = 0;
        for (Tweet tweet : tweets.values()) {
            if (tweet.getAuthor().getUsername().equals(username)) {
                count++;
            }
        }
        return count;
    }
}