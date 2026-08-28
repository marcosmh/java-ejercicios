package io.dev.code;

import java.util.PriorityQueue;

// Problema: Encuentra el k-ésimo elemento más grande en un array.

public class Main {

    public static int findKthLargest(int nums[], int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num : nums) {
            minHeap.add(num);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }


    public static void main(String [] args) {

        int[] nums = {3,2,1,5,6,4 };

        System.out.println( findKthLargest(nums, 2));

    }

}
