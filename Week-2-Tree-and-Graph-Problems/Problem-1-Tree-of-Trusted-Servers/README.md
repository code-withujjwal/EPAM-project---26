# Tree of Trusted Servers

## Problem Understanding

We are given a tree with `N` servers, where each server stores a security key represented by an integer. Server `1` is the root server.

A server is considered **trusted** if the XOR of all security keys on the path from the root server to that server is greater than or equal to a given threshold `K`.

Our task is to count the total number of trusted servers, including the root server.

---

# Key Observation

Since the given graph is a tree:

- There is exactly one unique path from the root to every node.
- We can compute the XOR value of a path incrementally while traversing the tree.
- If we already know the XOR value for a parent node, the XOR value for its child can be calculated in constant time.

Path XOR relation:

```text
pathXor[child] = pathXor[parent] ^ key[child]
```

This avoids recomputing the entire path repeatedly.

---

# Why DFS Works

Depth First Search (DFS) is suitable because we need to propagate information from a parent node to its children.

While traversing the tree, we maintain the current XOR value from the root to the current node.

Using an iterative DFS approach with an explicit stack:

- Each node is visited exactly once.
- The current path XOR is carried forward efficiently.
- The solution safely handles large trees without recursion depth issues.

---

# Data Structures Used

- Adjacency List for tree representation.
- Stack for iterative DFS traversal.
- Integer variables for maintaining path XOR values.
- Counter variable for tracking trusted servers.

---

# Algorithm

1. Build an adjacency list to represent the tree.
2. Create a stack for iterative DFS traversal.
3. Push the root node into the stack with:
   - Node = 1
   - Parent = 0
   - Path XOR = key[1]
4. While the stack is not empty:
   - Pop the current node.
   - Check whether its path XOR is greater than or equal to `K`.
   - If yes, increment the trusted server count.
   - Traverse all adjacent nodes.
   - Ignore the parent node.
   - Compute the child path XOR:
     
     ```text
     childPathXor = currentPathXor ^ key[child]
     ```
     
   - Push the child into the stack.
5. After traversal completes, return the total trusted server count.

---

# Dry Run

### Input

```text
N = 3
K = 2

Keys:
1 -> 3
2 -> 1
3 -> 2

Edges:
1 2
1 3
```

### Traversal

#### Node 1

```text
pathXor = 3
```

```text
3 >= 2
```

Trusted Count = 1

---

#### Node 2

```text
pathXor = 3 ^ 1
         = 2
```

```text
2 >= 2
```

Trusted Count = 2

---

#### Node 3

```text
pathXor = 3 ^ 2
         = 1
```

```text
1 >= 2
```

Not Trusted

---

### Final Answer

```text
Trusted Servers = 2
```

---

# Complexity Analysis

### Time Complexity

```text
O(N)
```

- Every node is processed exactly once.
- Every edge is visited at most twice.

---

### Space Complexity

```text
O(N)
```

- Adjacency List requires O(N) space.
- Iterative DFS stack may store up to O(N) nodes in the worst case.

---

# Learning Outcomes

- Tree Representation using Adjacency List.
- Depth First Search (DFS).
- Iterative DFS using Stack.
- XOR Operations on Tree Paths.
- Efficient State Propagation in Trees.
- Solving Graph Problems under Large Constraints.