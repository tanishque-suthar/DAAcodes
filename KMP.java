import java.util.Scanner;

public class KMP {
    // Compute the prefix function for KMP algorithm
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        lps[0] = 0;  // lps[0] is always 0
        //ABABCABAB
        int j = 0, i = 1; 
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    // KMP search algorithm
    private static void KMPSearch(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        
        // Create lps[] that will hold the longest prefix suffix values for pattern
        int[] lps = computeLPSArray(pattern);
        
        int i = 0;  // index for text
        int j = 0;  // index for pattern
        boolean found = false;
        
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                found = true;
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch after j matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        if (!found) {
            System.out.println("Pattern not found in the text");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        
        System.out.println("Enter the pattern to search:");
        String pattern = sc.nextLine();
        
        KMPSearch(pattern, text);
        sc.close();
    }
}