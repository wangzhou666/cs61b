import java.util.Map;
import java.util.HashMap;

public class UsernameBank {

    // Instance variables (remember, they should be private!)
    // YOUR CODE HERE
    private Map<String, String> mapUsernameEmail;
    private Map<String, String> mapEmailUsername;
    private Map<String, Integer> badEmailCounts;
    private Map<String, Integer> badUsernamesCounts;

    public UsernameBank() {
        // YOUR CODE HERE
        mapEmailUsername = new HashMap<String, String>();
        mapUsernameEmail = new HashMap<String, String>();
        badEmailCounts = new HashMap<String, Integer>();
        badUsernamesCounts = new HashMap<String, Integer>();
    }

    public void generateUsername(String username, String email) {
        // YOUR CODE HERE
        if (validUsername(username) & validEmail(email)) {
            if (!mapUsernameEmail.containsKey(username)) {
                mapUsernameEmail.put(username, email);
                mapEmailUsername.put(email, username);
            }
            return;
        }
        if (username == null | email == null) {
            throw new NullPointerException("Invalid input: username or email is null.");
        }
        if (!validUsername(username)) {
            throw new IllegalArgumentException("Invalid username.");
        }
        if (validUsername(username) & mapUsernameEmail.containsKey(username)) {
            throw new IllegalArgumentException("Username has already existed.");
        }
        return;
    }

    private boolean validUsername(String username) {
        char[] arrayName = username.toCharArray();
        for (char c : arrayName) {
            if ((int) c > 122 | (int) c < 48 | ((int) c > 57 & (int) c < 65) | ((int) c > 90 & (int) c < 97)) {
                return false;
            }
        }
        return true;
    }

    private boolean validEmail(String email) {
        char[] arrayEmail = email.toCharArray();
        int countAt = 0;
        for (char c : arrayEmail) {
            if (c == '@') {
                countAt++;
            }
        }
        return countAt == 1;
    }

    public String getEmail(String username) {
        // YOUR CODE HERE
        if (username == null) {
            throw new NullPointerException("null username");
        }
        if (!validUsername(username)) {
            if (badUsernamesCounts.containsKey(username)) {
                Integer count = (Integer) badUsernamesCounts.get(username);
                Integer newcount = new Integer(count.intValue() + 1);
                badUsernamesCounts.remove(username);
                badUsernamesCounts.put(username, newcount);
            } else {
                badUsernamesCounts.put(username, new Integer(1));
            }
            return null;
        } else {
            if (!mapUsernameEmail.containsKey(username)) {
                if (badUsernamesCounts.containsKey(username)) {
                    Integer count = (Integer) badUsernamesCounts.get(username);
                    Integer newcount = new Integer(count.intValue() + 1);
                    badUsernamesCounts.remove(username);
                    badUsernamesCounts.put(username, newcount);
                } else {
                    badUsernamesCounts.put(username, new Integer(1));
                }
                return null;
            } else {
                return mapUsernameEmail.get(username);
            }
        }

        
    }

    public String getUsername(String userEmail)  {
        // YOUR CODE HERE
        if (userEmail == null) {
            throw new NullPointerException("invalid email address");
        }
        if (mapEmailUsername.containsKey(userEmail)) {
            return mapEmailUsername.get(userEmail);
        } else {
            if (badEmailCounts.containsKey(userEmail)) {
                    Integer count = (Integer) badEmailCounts.get(userEmail);
                    Integer newcount = new Integer(count.intValue() + 1);
                    badEmailCounts.remove(userEmail);
                    badEmailCounts.put(userEmail, newcount);
                } else {
                    badEmailCounts.put(userEmail, new Integer(1));
                }
                return null;
        }
    }

    public Map<String, Integer> getBadEmails() {
        // YOUR CODE HERE
        return badEmailCounts;
    }

    public Map<String, Integer> getBadUsernames() {
        // YOUR CODE HERE
        return badUsernamesCounts;
    }

    public String suggestUsername() {
        // YOUR CODE HERE
        int i = 0;
        String username;
        do {
            username = randomName();
            i++;
            if (i > 100) {
                break;
            }
        } while(mapUsernameEmail.containsKey(username));
        return username;
    }

    private String randomName() {
        char[] generatedChar = new char[3];
        for (int i = 0; i < 3; i++) {
            double randomSeed = Math.random() * 36;
            if (randomSeed < 26) {
                generatedChar[i] = (char) (Math.floor(randomSeed) + 97);
            } else {
                if (randomSeed == 36) {
                    randomSeed = 35;
                }
                generatedChar[i] = (char) (Math.floor(randomSeed) + 22);
            }
        }
        String username = new String(generatedChar);
        return username;
    }


    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
        // YOUR CODE HERE

    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
        // YOUR CODE HERE
    }
}