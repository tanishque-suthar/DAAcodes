import java.util.Scanner;

public class rabinKarp {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text string:");
        String text = sc.nextLine();
        System.out.println("Enter the pattern to search for:");
        String pattern = sc.nextLine();
        search(text, pattern);
        
        sc.close();
    }

    public static void search(String text, String pattern) {
        int patternLength = pattern.length(); //m
        int textLength = text.length(); //n
        boolean found = false;
        
        int patternHash = 0, windowHash = 0;
        
        for (int i = 0; i < patternLength; i++) {
            patternHash = (patternHash * 26 + pattern.charAt(i)) % 101;
            windowHash = (windowHash * 26 + text.charAt(i)) % 101;
        }
        
        // Calculate 26^(m-1) % 101 for rolling hash
        int highestPower = 1;
        for (int i = 0; i < patternLength - 1; i++) {
            highestPower = (highestPower * 26) % 101;
        }
        
        for (int i = 0; i <= textLength - patternLength; i++) {
            if (patternHash == windowHash) {
                boolean match = true;
                for (int j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at index " + i);
                    found = true;
                }
            }
            // Update hash for next window using rolling hash technique
            if (i < textLength - patternLength) {
                // Remove leading digit, add trailing digit
                windowHash = (
                    26 * (windowHash - text.charAt(i) * highestPower) % 101 + 
                    text.charAt(i + patternLength)) % 101;

                if (windowHash < 0) {
                    windowHash += 101;
                }
            }
        }
        if (!found) {
            System.out.println("Pattern not found in the text");
        }
    }
}