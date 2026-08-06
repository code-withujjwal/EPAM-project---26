# Efficiently Reachable Cities

## Concepts Used

- Graphs
- BFS
- Queue
- Shortest Path
- Adjacency List

## Approach

The problem requires finding the number of efficiently reachable cities starting from City 1, where the condition for "efficiently reachable" is that the shortest path is less than or equal to a given distance `D`. Since all highway edges are unweighted and bidirectional, the Breadth-First Search (BFS) algorithm is the most optimal approach. 

BFS explores the graph level by level, ensuring that the first time a city is visited, the shortest path to it has been found. We compute the shortest distance to each city by leveraging a Queue and a Distance array, and finally count how many cities satisfy the condition `distance != -1` AND `distance <= D`.

## Key Formula

```java
distance[neighbor] = distance[current] + 1
```

## Complexity

Time Complexity: O(N + M)

Space Complexity: O(N)

## Learning Outcomes

- Graph Representation
- BFS Traversal
- Shortest Path in Unweighted Graph
- Queue Usage
- Distance Computation
