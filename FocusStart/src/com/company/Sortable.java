package com.company;

import java.util.List;

public interface Sortable<T> {

    List<T> getList();

    List<T> readFIle();

    void checkSorting();

    Sortable<T> merge(List<T> right);
}
