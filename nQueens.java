import java.util.Scanner;

public class nQueens {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of N for N-Queens problem: ");
        int n = sc.nextInt();
        sc.close();
    
        int[][] board = new int[n][n];
        
        if (solveNQueens(board, 0, n)) {
            System.out.println("Solution exists. The placement of queens is:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for N = " + n);
        }
    }
    
    // A utility function to print the solution
    private static void printBoard(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // A utility function to check if a queen can be placed at board[row][col]
    private static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        
        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        // Check lower diagonal on left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }
    
    // A recursive utility function to solve N Queens problem
    private static boolean solveNQueens(int[][] board, int col, int n) {
        // Base case: If all queens are placed, return true
        if (col >= n) {
            return true;
        }
        
        // Consider this column and try placing queen in all rows one by one
        for (int i = 0; i < n; i++) {
            // Check if queen can be placed at board[i][col]
            if (isSafe(board, i, col, n)) {
                // Place this queen at board[i][col]
                board[i][col] = 1;
                // Recur to place rest of the queens
                if (solveNQueens(board, col + 1, n)) {
                    return true;
                }
                // If placing queen in board[i][col] doesn't lead to a solution,
                // remove queen from board[i][col]
                board[i][col] = 0; // Backtrack
            }
        }
        return false;
    }
}