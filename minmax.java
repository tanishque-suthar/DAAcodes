import java.util.*;
public class minmax {
    static int max, min;
    public static void findMaxMin(int[] arr, int i, int j) {
        if (i == j) {  // Only one element
            max = min = arr[i];
        }

        else if (i == j - 1) {  // Two elements
            if (arr[i] < arr[j]) {
                max = arr[j];
                min = arr[i];
            } else {
                max = arr[i];
                min = arr[j];
            }
        }

        else {
            int mid = (i + j) / 2;
            int max1, min1;
            findMaxMin(arr, i, mid);
            //System.out.println(("max1:" + max));
            max1 = max;
            min1 = min;
            findMaxMin(arr, mid + 1, j);
            //System.out.println(("max2:" + max));
            if (max < max1) {
                max = max1;
            }
            if (min > min1) {
                min = min1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        findMaxMin(arr, 0, arr.length - 1);

        System.out.println("Maximum value: " + max);
        System.out.println("Minimum value: " + min);
    }
}