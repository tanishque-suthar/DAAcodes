import java.util.*;
public class knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter the number of items: ");
        n = sc.nextInt();
        System.out.println("Enter the capacity of the knapsack: ");
        int c = sc.nextInt();
        int[] p = new int[n];
        int[] w = new int[n];
        System.out.println("Enter the profit of each item: ");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        System.out.println("Enter the weight of each item: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        double[] pByW = new double[n];
        for (int i = 0; i < n; i++) {
            pByW[i] = (double) (p[i] / w[i]);
        }
        int temp;
        double tempPByW;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (pByW[i] > pByW[j]) {
                    tempPByW = pByW[i];
                    pByW[i] = pByW[j];
                    pByW[j] = tempPByW;

                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;

                    temp = w[i];
                    w[i] = w[j];
                    w[j] = temp;
                }
            }
        }
        int remainingCapacity = c;
        int x = 0;
        double maxProfit = 0;
        while(remainingCapacity > 0 && x < n) {
            if (w[x] <= remainingCapacity) {
                remainingCapacity -= w[x];
                maxProfit += p[x];
            } else {
                maxProfit += p[x] * (double)(remainingCapacity / w[x]);
                remainingCapacity = 0;
            }
            x++;
        }
        System.out.println("Maximum profit: " + maxProfit);
    }
}