package com.ig833;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    private static <E> void printArr(E[] arr) {
        for (E element : arr) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // determines the largest of three Comparable objects
    private static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x; // assume x is initially the largest
        if (y.compareTo(max) > 0) {
            max = y; // y is the largest so far
        }
        if (z.compareTo(max) > 0) {
            max = z; // z is the largest now
        }
        return max; // returns the largest object
    }

    public static void main(String[] args) {
        printArr(new Integer[]{1, 2, 3 ,4});
        printArr(new String[]{"Gosho", "Pesho", "Tosho"});
        printArr(new Double[]{3.14, -13.12, 191.4});
        System.out.printf("Max of %d, %d and %d is %d\n\n",
                3, 4, 5, maximum(3, 4, 5));

        System.out.printf("Maxm of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));

        System.out.printf("Max of %s, %s and %s is %s\n", "pear",
                "apple", "orange", maximum("pear", "apple", "orange"));

        NumbersContainer<Double, ArrayList<Double>> doubleContainer =
                new NumbersContainer<>(new ArrayList<>());
        doubleContainer.add(4.2);
        doubleContainer.add(2.8);
        System.out.println("Sum is " + doubleContainer.sum());
        NumbersContainer<Integer, LinkedList<Integer>> integerContainer =
                new NumbersContainer<>(new LinkedList<>());
        integerContainer.add(3);
        integerContainer.add(4);
        System.out.println("Sum is " + integerContainer.sum());
    }
}
