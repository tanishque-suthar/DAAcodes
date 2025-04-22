import java.util.Scanner;

public class LCS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the first string:");
        String s1 = scanner.nextLine();
        
        System.out.println("Enter the second string:");
        String s2 = scanner.nextLine();
        
        scanner.close();
        
        System.out.println("Length of Longest Common Subsequence: " + findLCS(s1, s2));
        System.out.println("The Longest Common Subsequence is: " + printLCS(s1, s2));
    }
    
    public static int findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Create a table to store the lengths of LCS
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the dp table in bottom-up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static String printLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // Create a table to store the lengths of LCS
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // Backtrack to find the actual LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        
        return lcs.reverse().toString();
    }
}