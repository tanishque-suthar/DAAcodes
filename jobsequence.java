import java.util.*;

class Job {
    char id;
    int deadline;
    int profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
    public int getProfit() {
        return profit;
    }
}

public class jobsequence {

    // Custom sorting method to sort jobs in descending order of profit
    public static void sortJobs(Job[] jobs, int n) {
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (jobs[j].profit < jobs[j+1].profit) {
                    // Swap jobs[j] and jobs[j+1]
                    Job temp = jobs[j];
                    jobs[j] = jobs[j+1];
                    jobs[j+1] = temp;
                }
            }
        }
    }

    public static void printJobScheduling(Job[] jobs, int n) {
        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> Integer.compare(b.getProfit(), a.getProfit()));

        // Find the maximum deadline
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        // To keep track of free time slots
        boolean[] slot = new boolean[maxDeadline];
        char[] result = new char[maxDeadline];

        // Initialize all slots to be free
        for (int i = 0; i < maxDeadline; i++) {
            slot[i] = false;
        }

        // Iterate through all given jobs
        for (int i = 0; i < n; i++) {
            // Find a free slot for this job
            // We start from the last possible slot
            for (int j = Math.min(maxDeadline - 1, jobs[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }

        // Print the result
        System.out.println("\nFollowing is the maximum profit sequence of jobs:");
        int totalProfit = 0;
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
                // Find and add the profit for this job
                for (int j = 0; j < n; j++) {
                    if (jobs[j].id == result[i]) {
                        totalProfit += jobs[j].profit;
                        break;
                    }
                }
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of jobs:");
        int n = scanner.nextInt();
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for job " + (i + 1) + ":");
            System.out.print("ID (single character): ");
            char id = scanner.next().charAt(0);
            
            System.out.print("Deadline: ");
            int deadline = scanner.nextInt();
            
            System.out.print("Profit: ");
            int profit = scanner.nextInt();
            
            jobs[i] = new Job(id, deadline, profit);
        }

        printJobScheduling(jobs, n);
    }
}