package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringSort implements Sortable<String> {

    private String fileName;
    private List<String> list;

    public StringSort(String fileName) {
        this.fileName = fileName;
        list = new ArrayList<>();
        readFIle();
        checkSorting();
    }

    @Override
    public List<String> getList() {
        return list;
    }

    @Override
    public List<String> readFIle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл \"%s\" не найден!\n", fileName);
        } catch (IOException e) {
            System.out.printf("Произошла ошибка при чтениии файла: \"%s\"!\n", fileName);
        }
        return list;
    }

    @Override
    public Sortable<String> merge(List<String> right) {
        int size = list.size() + right.size();
        List<String> result = new ArrayList<>();

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

    @Override
    public void checkSorting() {
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i-1)).compareTo(list.get(i)) > 0) {
                list = list.subList(0, i);
            }
        }
    }
}
