import java.util.*;

public class Solution {

    /**
     * Finds the number of efficiently reachable cities from City 1.
     * A city is efficiently reachable if its shortest distance from City 1 is <= D.
     * 
     * @param n the number of cities
     * @param m the number of highways
     * @param d the maximum allowed distance D
     * @param edges the array of edges representing highways between cities
     * @return the number of efficiently reachable cities
     */
    public int countEfficientlyReachableCities(int n, int m, int d, int[][] edges) {
        // Step 1: Build the Adjacency List representing the graph
        List<List<Integer>> graph = new ArrayList<>();
        // 1-based indexing for cities, so we use size n + 1
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // Graph is undirected
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // Step 2: Initialize distance array
        // distance[i] will store the shortest distance from City 1 to City i
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1); // -1 indicates unvisited
        
        // Step 3: Perform BFS from City 1
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // Start from City 1
        distance[1] = 0; // Distance to itself is 0
        
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            
            // Explore all neighbors of the current city
            for (int neighborCity : graph.get(currentCity)) {
                // If neighbor is unvisited, we found its shortest path
                if (distance[neighborCity] == -1) {
                    distance[neighborCity] = distance[currentCity] + 1;
                    queue.add(neighborCity);
                }
            }
        }
        
        // Step 4: Count the reachable cities within distance D
        int reachableCount = 0;
        for (int i = 1; i <= n; i++) {
            // Check distance condition
            if (distance[i] != -1 && distance[i] <= d) {
                reachableCount++;
            }
        }
        
        return reachableCount;
    }
}
