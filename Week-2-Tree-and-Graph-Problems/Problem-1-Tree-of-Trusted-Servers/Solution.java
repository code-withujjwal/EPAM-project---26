import java.util.*;

class Solution {

    public int countTrustedServers(int n, int k, int[] keys, int[][] edges) {

        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build tree
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] trustedCount = new int[1];

        // Start DFS from root node 0
        dfs(0, -1, 0, adj, keys, k, trustedCount);

        return trustedCount[0];
    }


    private void dfs(
            int node,
            int parent,
            int currentXor,
            List<List<Integer>> adj,
            int[] keys,
            int k,
            int[] trustedCount
    ) {

        // Include current node key in path XOR
        currentXor ^= keys[node];

        // Check if current server is trusted
        if (currentXor >= k) {
            trustedCount[0]++;
        }

        // Visit children
        for (int neighbor : adj.get(node)) {

            if (neighbor != parent) {
                dfs(
                    neighbor,
                    node,
                    currentXor,
                    adj,
                    keys,
                    k,
                    trustedCount
                );
            }
        }
    }
}
