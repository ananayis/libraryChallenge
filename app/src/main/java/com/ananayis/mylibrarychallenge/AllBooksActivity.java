package com.ananayis.mylibrarychallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);

        booksRecView = findViewById(R.id.booksRecView);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Ansichten eines Clowns", " Heinrich Theodor Böll", 353,"https://m.media-amazon.com/images/I/41w5XkpxO9L.jpg",
                "This book is a novel by West German writer Heinrich Böll.","Following publication in 1963, the novel generated polemics in the press for its negative portrayal of the Catholic Church and the CDU party. Böll's liberal views on religion and social issues inspired the wrath of conservatives in Germany.[1] The conservative press even attacked Böll's 1972 Nobel prize award, arguing that it was awarded only to \"liberals and left-wing radicals."));

        adapter.setBooks(books);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));


    }
}