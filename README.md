# Algorithm Performance Analyzer

A Java benchmarking project that compares sorting and searching algorithms across multiple input sizes using measured execution times.

## What It Compares

### Sorting
- Selection Sort
- Java's `Arrays.sort()`

### Searching
- Linear Search
- Binary Search using `Arrays.binarySearch()`

## Features

- Generates reproducible randomized integer datasets
- Tests multiple input sizes
- Copies datasets so sorting algorithms receive equivalent input
- Measures execution time with `System.nanoTime()`
- Reports results in milliseconds
- Demonstrates the practical effect of algorithmic time complexity

## Complexity

| Algorithm | Time Complexity |
| --- | --- |
| Selection Sort | O(n²) |
| Linear Search | O(n) |
| Binary Search | O(log n) |
| `Arrays.sort(int[])` | O(n log n) worst case |

Binary search requires sorted input. The benchmark sorts its search datasets before comparing linear and binary search.

## Run

Compile and run from the repository root:

```bash
javac src/AlgorithmPerformanceAnalyzer.java -d out
java -cp out AlgorithmPerformanceAnalyzer
```

## Input Sizes

The program currently benchmarks:

```
1,000
5,000
10,000
20,000
```

These sizes keep the quadratic selection sort benchmark practical while still making its scaling behavior visible.

## Benchmarking Note

This project is intended as an educational algorithm comparison rather than a production Java benchmark. JVM startup and warmup, JIT compilation, hardware, and background processes can affect individual timing results. For rigorous JVM microbenchmarking, a framework such as JMH would be more appropriate.

## Concepts Demonstrated

Java arrays, sorting algorithms, searching algorithms, algorithmic complexity analysis, defensive array copying, randomized test data, and basic performance measurement.
