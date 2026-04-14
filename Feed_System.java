import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Feed_System {
    public void showFeed(Tweet_System tweetService) {
        // Convert map values to a List
        List<Tweet> feedList = new ArrayList<>(tweetService.getAllTweets().values());
        
        // Collection method to reverse the list so newest is at the top
        Collections.reverse(feedList);

        System.out.println("=== TWITTER FEED ===");
        for (Tweet tweet : feedList) {
            Interactable interactableTweet = tweet; // Polymorphism in action
            interactableTweet.displayTweet();
        }
    }
}