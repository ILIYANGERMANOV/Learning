import utils.SortUtils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SortUtils sortUtils = new SortUtils();
        int[] arr = {100, 5, 2, 4, 3, 1, -20};
        int[] tmpArr = new int[arr.length];
        System.out.println(Arrays.toString(sortUtils.mergeSortTopDown(arr, tmpArr, arr.length)));
    }
}
