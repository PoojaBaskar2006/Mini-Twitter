import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        User_System userService = new User_System();
        Tweet_System tweetService = new Tweet_System();
        Feed_System feedService = new Feed_System();
        Notification_System notificationService = new Notification_System();
        Like_System likeService = new Like_System();
        Comment_System commentService = new Comment_System();


        User currentUser = null;
        boolean running = true;

        System.out.println("Welcome to Mini Twitter!");

        while (running) {
            System.out.println("\n1.Register 2.Login 3.Logout 4.MyProfile 5.OtherProfile 6.CreateTweet 7.DeleteTweet 8.ViewFeed");
            System.out.println("9.Like 10.Unlike 11.Comment 12.ViewComments 13.DeleteComment 14.Notifications 15.UpdateBio 0.Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // ==========================================
            // STRICT LOGIN GATE
            // Blocks options 3 through 15 if not logged in
            // ==========================================
            if (choice >= 3 && choice <= 15 && currentUser == null) {
                System.out.println("\n ACCESS DENIED: You must register or login first to use this feature.\n");
                continue; // Skips the switch and restarts the menu loop
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPass = scanner.nextLine();
                    userService.register(regUser, regPass);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    currentUser = userService.login(loginUser, loginPass);
                    break;
                case 3:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                case 4:
                    userService.viewProfile(currentUser, tweetService);
                    break;
                case 5:
                    System.out.print("Enter username to view: ");
                    String targetUser = scanner.nextLine();
                    userService.viewProfile(currentUser, targetUser, tweetService);
                    break;
                case 6:
                    System.out.print("What's happening? ");
                    String text = scanner.nextLine();
                    tweetService.createTweet(currentUser, text);
                    break;
                case 7:
                    System.out.print("Enter Tweet ID to delete: ");
                    String delId = scanner.nextLine();
                    tweetService.deleteTweet(delId, currentUser);
                    break;
                case 8:
                    feedService.showFeed(tweetService);
                    break;
                case 9:
                    System.out.print("Enter Tweet ID to like: ");
                    String likeId = scanner.nextLine();
                    Tweet tLike = tweetService.getTweet(likeId);
                    if (tLike != null) likeService.likeTweet(tLike, currentUser, notificationService);
                    else System.out.println("Tweet not found.");
                    break;
                case 10:
                    System.out.print("Enter Tweet ID to unlike: ");
                    String unlikeId = scanner.nextLine();
                    Tweet tUnlike = tweetService.getTweet(unlikeId);
                    if (tUnlike != null) likeService.unlikeTweet(tUnlike, currentUser);
                    else System.out.println("Tweet not found.");
                    break;
                case 11:
                    System.out.print("Enter Tweet ID to comment on: ");
                    String commId = scanner.nextLine();
                    Tweet tComm = tweetService.getTweet(commId);
                    if (tComm != null) {
                        System.out.print("Enter comment: ");
                        String cText = scanner.nextLine();
                        commentService.addComment(tComm, currentUser, cText, notificationService);
                    } else System.out.println("Tweet not found.");
                    break;
                case 12:
                    System.out.print("Enter Tweet ID to view comments: ");
                    String vCommId = scanner.nextLine();
                    commentService.viewComments(vCommId);
                    break;
                case 13:
                    System.out.print("Enter Tweet ID: ");
                    String dTweetId = scanner.nextLine();
                    Tweet dtweet = tweetService.getTweet(dTweetId);
                    if (dtweet != null) {
                        System.out.print("Enter Comment ID to delete: ");
                        String dCommId = scanner.nextLine();
                        commentService.deleteComment(dtweet, dCommId, currentUser);
                    } else System.out.println("Tweet not found.");
                    break;
                case 14:
                    notificationService.viewNotifications(currentUser);
                    break;
                case 15:
                    System.out.print("Enter your new bio: ");
                    String newBio = scanner.nextLine();
                    currentUser.setBio(newBio);
                    System.out.println("Bio updated successfully!");
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}