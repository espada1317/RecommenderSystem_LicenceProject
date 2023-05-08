package com.example.recsys.comparators.books.reviews;

import com.example.recsys.entity.BookReview;

import java.util.Comparator;

public class BookReviewRatingComparator implements Comparator<BookReview> {
    @Override
    public int compare(BookReview o1, BookReview o2) {
        double o1_rating = o1.getBooks().getRating();
        double o2_rating = o2.getBooks().getRating();
        return Double.compare(o1_rating, o2_rating);
    }
}
