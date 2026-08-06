import java.util.*;

public class Solution {

    
     
    public int countEfficientlyReachableCities(int n, int m, int d, int[][] edges) {
       
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
           
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
      
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        
    
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); 
        distance[1] = 0; 
        
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            
           
            for (int neighborCity : graph.get(currentCity)) {
               
                if (distance[neighborCity] == -1) {
                    distance[neighborCity] = distance[currentCity] + 1;
                    queue.add(neighborCity);
                }
            }
        }
        
      
        int reachableCount = 0;
        for (int i = 1; i <= n; i++) {
          
            if (distance[i] != -1 && distance[i] <= d) {
                reachableCount++;
            }
        }
        
        return reachableCount;
    }
}
