package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerSort implements Sortable<Integer> {

    private String fileName;
    private List<Integer> list;

    public IntegerSort(String fileName) {
        this.fileName = fileName;
        list = new ArrayList<>();
        readFIle();
        checkSorting();
    }

    @Override
    public List<Integer> getList() {
        return list;
    }

    @Override
    public List<Integer> readFIle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                createList(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Sortable<Integer> merge(List<Integer> right) {
        int size = list.size() + right.size();
        List<Integer> result = new ArrayList<>();

        int l = 0;
        int r = 0;

        for (int i = 0; i < size; i++) {
            if (r >= right.size()) {
                result.add(list.get(l));
                l++;
            } else if (l < list.size() && list.get(l).compareTo(right.get(r)) < 1) {
                result.add(list.get(l));
                l++;
            } else {
                result.add(right.get(r));
                r++;
            }
        }
        list = result;
        return this;
    }

    private void createList(String s) {
        try {
            int number = Integer.parseInt(s);
            list.add(number);
        } catch (NumberFormatException ignore) {}
    }

    @Override
    public void checkSorting() {
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i-1)).compareTo(list.get(i)) > 0) {
                list = list.subList(0, i);
            }
        }
    }
}
