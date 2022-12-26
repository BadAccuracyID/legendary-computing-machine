package com.github.badaccuracyid.legendarycomputingmachine.utils;

import com.github.badaccuracyid.legendarycomputingmachine.objects.UserData;

import java.util.List;
import java.util.function.Consumer;

public class Sorter {

    public static final Consumer<List<UserData>> mergeSort = (list) -> mergeSortUserData(list, 0, list.size() - 1);

    private static void mergeSortUserData(List<UserData> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortUserData(list, left, mid);
            mergeSortUserData(list, mid + 1, right);

            mergeSortUser(list, left, mid, right);
        }
    }

    private static void mergeSortUser(List<UserData> list, int left, int mid, int right) {
        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;

        UserData[] leftArr = new UserData[leftArrSize];
        UserData[] rightArr = new UserData[rightArrSize];

        for (int i = 0; i < leftArrSize; i++) {
            leftArr[i] = list.get(left + i);
        }

        for (int i = 0; i < rightArrSize; i++) {
            rightArr[i] = list.get(mid + 1 + i);
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int currIndex = left;

        while (leftIndex < leftArrSize && rightIndex < rightArrSize) {
            // compare by rating
            if (leftArr[leftIndex].getHighScore() >= rightArr[rightIndex].getHighScore()) {
                list.set(currIndex, leftArr[leftIndex]);
                leftIndex++;
            } else {
                list.set(currIndex, rightArr[rightIndex]);
                rightIndex++;
            }
            currIndex++;
        }

        while (leftIndex < leftArrSize) {
            list.set(currIndex, leftArr[leftIndex]);
            leftIndex++;
            currIndex++;
        }

        while (rightIndex < rightArrSize) {
            list.set(currIndex, rightArr[rightIndex]);
            rightIndex++;
            currIndex++;
        }
    }
}
