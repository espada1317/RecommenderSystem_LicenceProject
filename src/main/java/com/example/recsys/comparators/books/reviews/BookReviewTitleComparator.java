package com.example.recsys.comparators.books.reviews;

import com.example.recsys.entity.BookReview;

import java.util.Comparator;

public class BookReviewTitleComparator implements Comparator<BookReview> {
    @Override
    public int compare(BookReview o1, BookReview o2) {
        return o1.getBooks().getTitle().compareTo(o2.getBooks().getTitle());
    }
}
