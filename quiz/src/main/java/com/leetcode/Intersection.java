package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by roger on 5/30/16.
 */
public class Intersection {
    public static int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        for(int num : nums1) {
            set.add(num);
        }
        Set<Integer> resultSet = new HashSet<>();
        for(int num : nums2) {
            if(set.contains(num)) {
                resultSet.add(num);
            }
        }
        int i = 0;
        int[] result = new int[resultSet.size()];
        for(int num : resultSet) {
            result[i] = num;
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};

        int[] result = intersection(nums1, nums2);
        for( int num: result) {
            System.out.print(num);
        }
    }
}
