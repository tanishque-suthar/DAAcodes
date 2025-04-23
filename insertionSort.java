import java.util.Scanner;
public class insertionSort{
    public static void sort(int[] arr, int n){
        int key=0,j=0;
        for(int i=0; i<n;i++){
            key=arr[i];
            j=i-1;
            while(j>-1 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }
    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements:");
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        sort(arr,n);
        System.out.println("Sorted array:");
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
}