import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Like_System {
    // Map of TweetID to a Set of Usernames
    private Map<String, Set<String>> likesData = new HashMap<>();

    public void likeTweet(Tweet tweet, User user, Notification_System notifyService) {
        String tweetId = tweet.getId();
        
        if (!likesData.containsKey(tweetId)) {
            likesData.put(tweetId, new HashSet<>());
        }

        Set<String> likedUsers = likesData.get(tweetId);
        
        // Set.add() returns true only if the user wasn't already in the set
        if (likedUsers.add(user.getUsername())) {
            tweet.addLike();
            System.out.println("Tweet liked!");
            notifyService.sendNotification(tweet.getAuthor().getUsername(), user.getUsername() + " liked your tweet.");
        } else {
            System.out.println("You already liked this tweet.");
        }
    }

    public void unlikeTweet(Tweet tweet, User user) {
        String tweetId = tweet.getId();
        if (likesData.containsKey(tweetId)) {
            Set<String> likedUsers = likesData.get(tweetId);
            if (likedUsers.remove(user.getUsername())) {
                tweet.removeLike();
                System.out.println("Tweet unliked!");
            }
        }
    }
}