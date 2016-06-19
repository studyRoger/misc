//https://leetcode.com/problems/intersection-of-two-arrays/

def intersection(array1: Array[Int], array2: Array[Int]): Array[Int] = array1.toSet.intersect(array2.toSet).toArray

val result1 = intersection(Array(1, 2, 2, 4), Array(2, 2))