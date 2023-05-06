package com.example.recsys.comparators.books;

import com.example.recsys.entity.Books;

import java.util.Comparator;

public class BooksTitleComparator implements Comparator<Books> {
    @Override
    public int compare(Books o1, Books o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
