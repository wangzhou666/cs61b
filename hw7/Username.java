public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
    private String username;

    public Username() {
        // YOUR CODE HERE
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
        username = new String(generatedChar);
    }

    public Username(String reqName) {
        // YOUR CODE HERE
        if (reqName == null) {
            throw new NullPointerException("Requested username is null!");
        }
        if (reqName.length() != 3) {
            throw new IllegalArgumentException("Illegal length: not equals to 3.");
        }
        char[] arrayName = reqName.toCharArray();
        for (char c : arrayName) {
            if ((int) c > 122 | (int) c < 48 | ((int) c > 57 & (int) c < 65) | ((int) c > 90 & (int) c < 97)) {
                throw new IllegalArgumentException("Illegal char detected:" + c + ".");
            }
        }
        username = reqName;
    }

    @Override
    public boolean equals(Object o) {
        // YOUR CODE HERE
        if (o instanceof Username) {
            Username that = (Username) o;
            char[] thisCharArray = username.toCharArray();
            char[] thatCharArray = that.username.toCharArray();
            for (int i = 0; i < 3; i++) {
                if (thisCharArray[i] >= 48 & thisCharArray[i] <= 57) {
                    if (thisCharArray[i] != thatCharArray[i]) {
                        return false;
                    } else {
                        continue;
                    }
                } else if (thisCharArray[i] >= 65 & thisCharArray[i] <= 90) {
                    if (thisCharArray[i] != thatCharArray[i] & thisCharArray[i] + 32 != thatCharArray[i]) {
                        return false;
                    } else {
                        continue;
                    }
                } else if (thisCharArray[i] >= 97 & thisCharArray[i] <= 122) {
                    if (thisCharArray[i] != thatCharArray[i] & thisCharArray[i] - 32 != thatCharArray[i]) {
                        return false;
                    } else {
                        continue;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() { 
        // YOUR CODE HERE
        char[] arrayName = username.toCharArray();
        for (char c : arrayName) {
            if ((int) c >= 97) {
                c -= 32;
            }
        }
        int code = 0;
        code += (int) arrayName[0];
        code += 100 * (int) arrayName[1];
        code += 10000 * (int) arrayName[2];
        return code;
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
        Username un = new Username();
        System.out.println(un.hashCode());
    }
}