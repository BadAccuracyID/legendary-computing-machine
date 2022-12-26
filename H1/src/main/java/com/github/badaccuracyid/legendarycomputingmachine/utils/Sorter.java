package com.github.badaccuracyid.legendarycomputingmachine.utils;

import com.github.badaccuracyid.legendarycomputingmachine.data.AnimeData;

import java.util.List;
import java.util.function.Consumer;

public class Sorter {

    public static final Consumer<List<AnimeData>> mergeSortPatients = (list) -> mergeSortAnimeData(list, 0, list.size() - 1);

    private static void mergeSortAnimeData(List<AnimeData> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortAnimeData(list, left, mid);
            mergeSortAnimeData(list, mid + 1, right);

            mergeSortAnime(list, left, mid, right);
        }
    }

    private static void mergeSortAnime(List<AnimeData> list, int left, int mid, int right) {
        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;

        AnimeData[] leftArr = new AnimeData[leftArrSize];
        AnimeData[] rightArr = new AnimeData[rightArrSize];

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
            if (leftArr[leftIndex].getRating() >= rightArr[rightIndex].getRating()) {
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
