import java.util.*;

public class kruskal {
    // A class to represent a weighted edge in graph
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge compareEdge) {
            // Using Integer.compare to avoid integer overflow
            return Integer.compare(this.weight, compareEdge.weight);
        }
    }
    
    // A class to represent a subset for union-find
    static class Subset {
        int parent, rank;
        
        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
    
    // Number of vertices and edges
    private int V, E;
    private Edge[] edges;
    
    // Constructor
    public kruskal(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }
    
    // Find set of an element i (uses path compression technique)
    private int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    
    // Union of two sets (uses union by rank)
    private void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);
        
        if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }
    
    // Main function to construct MST using Kruskal's algorithm
    public void kruskalMST() {
        // Result array to store the MST
        Edge[] result = new Edge[V - 1];
        
        // Index used for result array
        int e = 0;
        
        // Sort all edges in non-decreasing order of their weight
        Arrays.sort(edges);
        
        // Create V subsets with single elements
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; i++)
            subsets[i] = new Subset(i, 0);
        
        // Number of edges to be taken is V-1
        int i = 0;
        while (e < V - 1 && i < E) {
            // Pick the smallest edge
            Edge nextEdge = edges[i++];
            
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);
            
            // If including this edge doesn't cause a cycle, include it in result
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }
        
        // Print the constructed MST
        System.out.println("Edges in the Minimum Spanning Tree:");
        int totalWeight = 0;
        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            totalWeight += result[i].weight;
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter number of vertices:");
        int V = scanner.nextInt();
        
        System.out.println("Enter number of edges:");
        int E = scanner.nextInt();
        
        kruskal graph = new kruskal(V, E);
        
        System.out.println("Enter edge details (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            System.out.println("Edge " + (i + 1) + ":");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            
            graph.edges[i] = new Edge(src, dest, weight);
        }
        
        graph.kruskalMST();
        scanner.close();
    }
}