# Problem Understanding

Explain:
- Cities = Nodes
- Roads = Edges
- Need shortest distance from City 1
- Count cities whose distance <= D

# Key Observation

Explain:
- Graph is unweighted
- BFS always finds shortest path in unweighted graph

# Why BFS Works

Explain:
- BFS explores level by level
- First visit guarantees shortest distance

Important formula:

```java
distance[neighbor] = distance[current] + 1
```

# Data Structures Used

- Adjacency List
- Queue
- Distance Array

# Algorithm

1. Initialize an **Adjacency List** representation of the graph using the given highway edges.
2. Create a `distance` array of size `N + 1` initialized to `-1` to track shortest distances and unvisited cities.
3. Initialize a **Queue** and enqueue `City 1`. Set `distance[1] = 0`.
4. While the queue is not empty:
   - Dequeue the `currentCity`.
   - Iterate over each `neighborCity` connected to `currentCity`.
   - If `distance[neighborCity] == -1` (unvisited):
     - Update its distance: `distance[neighborCity] = distance[currentCity] + 1`.
     - Enqueue `neighborCity`.
5. After the BFS completes, iterate from `1` to `N` and count cities where `distance[i] != -1` AND `distance[i] <= D`.
6. Return the `reachableCount`.

# Dry Run

Use this sample:

Input:

```text
6 5 1

1 2
2 3
1 4
4 5
5 6
```

Output:

```text
3
```

Explain level-by-level BFS traversal:

- Queue initialized with `[1]`, `distance[1] = 0`.
- **Dequeue 1**, visit neighbors `2` and `4`.
  - `distance[2] = 0 + 1 = 1`. Queue: `[2, 4]`.
  - `distance[4] = 0 + 1 = 1`. Queue: `[2, 4]`.
- **Dequeue 2**, visit neighbor `3`.
  - `distance[3] = 1 + 1 = 2`. Queue: `[4, 3]`.
- **Dequeue 4**, visit neighbor `5`.
  - `distance[5] = 1 + 1 = 2`. Queue: `[3, 5]`.
- **Dequeue 3**, no unvisited neighbors. Queue: `[5]`.
- **Dequeue 5**, visit neighbor `6`.
  - `distance[6] = 2 + 1 = 3`. Queue: `[6]`.
- **Dequeue 6**, no unvisited neighbors. Queue: `[]`.

Count reachable nodes with distance `<= 1`:
- City 1: 0 (<= 1) -> Yes
- City 2: 1 (<= 1) -> Yes
- City 3: 2 (<= 1) -> No
- City 4: 1 (<= 1) -> Yes
- City 5: 2 (<= 1) -> No
- City 6: 3 (<= 1) -> No
Result: 3 efficiently reachable cities.

# Complexity Analysis

Time Complexity:
O(N + M)

Space Complexity:
O(N)

# Learning Outcomes

- Graph Representation
- BFS Traversal
- Shortest Path in Unweighted Graph
- Queue Usage
- Distance Computation
