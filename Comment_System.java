import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Comment {
    private String commentId;
    private String username;
    private String text;

    public Comment(String commentId, String username, String text) {
        this.commentId = commentId;
        this.username = username;
        this.text = text;
    }

    public String getCommentId() { return commentId; }
    public String getUsername() { return username; }
    public String getText() { return text; }
}

class Comment_System {
    // Map of TweetID to a List of Comments
    private Map<String, List<Comment>> commentsData = new HashMap<>();
    private int commentCounter = 1;

    public void addComment(Tweet tweet, User user, String text, Notification_System notifyService) {
        String tweetId = tweet.getId();
        String cId = "C" + commentCounter;
        commentCounter++;

        if (!commentsData.containsKey(tweetId)) {
            commentsData.put(tweetId, new ArrayList<>());
        }

        Comment newComment = new Comment(cId, user.getUsername(), text);
        commentsData.get(tweetId).add(newComment);
        tweet.addCommentCount();
        
        System.out.println("Comment added with ID: " + cId);
        notifyService.sendNotification(tweet.getAuthor().getUsername(), user.getUsername() + " commented: " + text);
    }

    public void viewComments(String tweetId) {
        if (commentsData.containsKey(tweetId)) {
            List<Comment> comments = commentsData.get(tweetId);
            System.out.println("--- Comments for " + tweetId + " ---");
            for (Comment c : comments) {
                System.out.println("[" + c.getCommentId() + "] " + c.getUsername() + ": " + c.getText());
            }
        } else {
            System.out.println("No comments on this tweet.");
        }
    }

    public void deleteComment(Tweet tweet, String commentId, User user) {
        String tweetId = tweet.getId();
        if (commentsData.containsKey(tweetId)) {
            List<Comment> comments = commentsData.get(tweetId);
            Comment commentToRemove = null;

            for (Comment c : comments) {
                if (c.getCommentId().equals(commentId) && c.getUsername().equals(user.getUsername())) {
                    commentToRemove = c;
                }
            }

            if (commentToRemove != null) {
                comments.remove(commentToRemove);
                tweet.removeCommentCount();
                System.out.println("Comment deleted.");
            } else {
                System.out.println("Cannot delete this comment.");
            }
        }
    }
}