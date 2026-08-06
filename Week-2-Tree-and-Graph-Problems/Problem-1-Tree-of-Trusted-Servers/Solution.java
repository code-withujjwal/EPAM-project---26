import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        // Using BufferedReader and StringTokenizer for fast I/O operations
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] keys = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            keys[i] = Integer.parseInt(st.nextToken());
        }
        
        // Using an adjacency list to represent the tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Reading N-1 edges
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // Array of size 1 to hold the count of trusted servers across recursive calls
        int[] trustedCount = new int[1];
        
        // Start DFS from node 1 (root), with parent as 0 and initial pathXor as 0.
        dfs(1, 0, 0, adj, keys, k, trustedCount);
        
        System.out.println(trustedCount[0]);
    }
    
    /**
     * Depth-First Search to traverse the tree and compute path XORs.
     * 
     * @param node The current node being visited.
     * @param parent The parent of the current node.
     * @param currentXor The XOR sum from the root down to the parent of the current node.
     * @param adj Adjacency list representing the tree.
     * @param keys The security keys of the servers.
     * @param k The threshold for a server to be considered trusted.
     * @param trustedCount Counter array holding the result.
     */
    private static void dfs(int node, int parent, int currentXor, List<List<Integer>> adj, int[] keys, int k, int[] trustedCount) {
        // Path XOR relation: pathXor[child] = pathXor[parent] ^ key[child]
        currentXor ^= keys[node];
        
        // If the path XOR is greater than or equal to K, the server is trusted
        if (currentXor >= k) {
            trustedCount[0]++;
        }
        
        // Traverse all neighbors
        for (int neighbor : adj.get(node)) {
            // Avoid traversing back to the parent to prevent infinite loops
            if (neighbor != parent) {
                dfs(neighbor, node, currentXor, adj, keys, k, trustedCount);
            }
        }
    }
}
