# Problem Understanding

We are given a tree with `N` nodes, where each node has an associated security key. The root of the tree is node 1. We need to find the total number of "trusted" servers. A server (node) is considered trusted if the XOR sum of all the security keys on the simple path from the root (node 1) to that server is greater than or equal to a given value `K`.

# Observation

- Since it's a tree, there is exactly one simple path from the root to any node.
- To compute the XOR sum from the root to a node, we don't need to traverse the path from scratch every time.
- Instead, we can maintain a running XOR sum as we traverse down the tree from the root.

# Why DFS works

Depth First Search (DFS) is an ideal algorithm for tree traversal when we need to pass down accumulated information (in this case, the XOR sum) from a parent to its children. It allows us to explore each path from root to leaf efficiently by maintaining state at each depth level.

# Path XOR relation

When moving from a `parent` node to a `child` node, the XOR sum of the path ending at `child` can be computed in O(1) time if we already know the XOR sum of the path ending at `parent`:

```
pathXor[child] = pathXor[parent] ^ key[child]
```

# Algorithm

1. Represent the tree using an Adjacency List `adj`.
2. Read the inputs: `N` (number of nodes), `K` (threshold), the array of `keys`, and the `N-1` edges.
3. Start a DFS traversal from the root node (node 1).
4. Maintain `currentXor` which stores the XOR sum from the root to the current node.
5. In the DFS function, update `currentXor = currentXor ^ key[node]`.
6. Check if `currentXor >= K`. If yes, increment the `trustedCount`.
7. Recursively call the DFS function for all neighbors of the current node, except its parent (to avoid infinite loops).
8. After exploring all nodes, print the `trustedCount`.

# Dry Run on sample

Assume a tree with `N=3`, `K=2`:
Keys: `keys = [0, 3, 1, 2]` (1-based indexing, index 0 is unused)
Edges: `(1, 2)`, `(1, 3)`

- Initial state: `trustedCount = 0`, `currentXor = 0`.
- Start DFS at Node 1:
  - `currentXor = 0 ^ keys[1] = 0 ^ 3 = 3`
  - `3 >= 2` (K) -> `trustedCount = 1`
  - DFS to neighbors: Node 2, Node 3.
- DFS at Node 2 (from Node 1):
  - `currentXor = 3 ^ keys[2] = 3 ^ 1 = 2`
  - `2 >= 2` (K) -> `trustedCount = 2`
  - No unvisited neighbors. Return.
- DFS at Node 3 (from Node 1):
  - `currentXor = 3 ^ keys[3] = 3 ^ 2 = 1`
  - `1 >= 2` is False.
  - No unvisited neighbors. Return.
- Final `trustedCount = 2`.

# Complexity Analysis

- **Time Complexity:** $O(N)$. We visit each node exactly once during the DFS traversal. Building the adjacency list also takes $O(N)$ time.
- **Space Complexity:** $O(N)$. The adjacency list stores $2 \times (N-1)$ elements for the undirected edges, taking $O(N)$ space. The maximum depth of the DFS recursion stack will be $O(N)$ in the worst case (e.g., a skewed tree/line graph). Thus, overall auxiliary space is $O(N)$.
