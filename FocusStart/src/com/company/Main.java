package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static boolean descending = false;
    private static boolean integerSort = false;
    private static String outputName;
    private static List<Sortable> sorting = new ArrayList<>();

    public static void main(String[] args) {
        rulesSort(args);
        if (sorting.size() > 1) {
            merge();
        }
        if (descending) {
            reverseSortingOrder();
        }
        writedList();
        System.out.println("Сортировка прошла успешно!");
    }

    private static void rulesSort(String[] args) {
        int lengthArray = args.length;
        for (int i = 0; i < lengthArray; i++) {
            switch (args[i]) {
                case "-a":
                    break;
                case "-s":
                    outputName = args[++i];
                    break;
                case "-d":
                    descending = true;
                    break;
                case "-i":
                    outputName = args[++i];
                    integerSort = true;
                    break;
                default:
                    initializationSort(args[i], integerSort);
            }
        }
    }

    private static void writedList() {
        List list = sorting.get(0).getList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
            }
        } catch (IOException e) {}
    }

    private static void initializationSort(String fileName, boolean integerSort) {
        Sortable sortable;
        if (integerSort) {
            sortable = new IntegerSort(fileName);
        } else {
            sortable= new StringSort(fileName);
        }
        sorting.add(sortable);
    }

    private static void merge() {
        for (int i = 1; i < sorting.size(); i++) {
            sorting.get(0).merge(sorting.get(i).getList());
        }
    }

    private static void reverseSortingOrder() {
        Collections.reverse(sorting.get(0).getList());
    }
}
