package utils;

public class SortUtils {
    public int[] mergeSortTopDown(int[] A, int[] B, int n) {
        return topDownSplitMerge(A, 0, n, B);
    }

    private int[] topDownSplitMerge(int[] arr, int iBegin, int iEnd, int[] tmpArr) {
        if (iEnd - iBegin < 2)                       // if run size == 1
            return null;                                 //   consider it sorted
        // recursively split runs into two halves until run size == 1,
        // then merge them and return back up the call chain
        int iMiddle = (iEnd + iBegin) / 2;              // iMiddle = mid point
        topDownSplitMerge(arr, iBegin, iMiddle, tmpArr);  // split / merge left  half
        topDownSplitMerge(arr, iMiddle, iEnd, tmpArr);  // split / merge right half
        topDownMerge(arr, iBegin, iMiddle, iEnd, tmpArr);  // merge the two half runs
        System.arraycopy(tmpArr, iBegin, arr, iBegin, iEnd - iBegin);             // copy the merged runs back to arr
        return arr;
    }

    private void topDownMerge(int[] arr, int iBegin, int iMiddle, int iEnd, int[] tmpArr) {
        int beginCounter = iBegin, middleCounter = iMiddle;

        // While there are elements in the left or right runs...
        for (int i = iBegin; i < iEnd; i++) {
            // If left run head exists and is <= existing right run head.
            if (beginCounter < iMiddle && (middleCounter >= iEnd || arr[beginCounter] <= arr[middleCounter])) {
                tmpArr[i] = arr[beginCounter++];
            } else {
                tmpArr[i] = arr[middleCounter++];
            }
        }
    }
}
