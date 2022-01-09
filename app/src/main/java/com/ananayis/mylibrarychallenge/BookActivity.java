package com.ananayis.mylibrarychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private TextView bookNameTxt, authorNameTxt, pagesNameTxt, descriptionTxt;
    private Button btnAddCurrently, btnAddWantToReed, btnAlreadyRead, btnAddFavorite;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Book book = new Book(2, "Legend", "David Gammell" , 320, "https://i.ebayimg.com/images/g/zsYAAOSwOOZguT01/s-l300.jpg" ,
                "This is the first and most famous novel of this writer.", "The Drenai Empire is under threat. The tribal Nadir people have been united for the first time by the great warleader Ulric, who has forged a massive empire in the North. ");


        setData(book);


    }

        private void initViews(){
            bookNameTxt = findViewById(R.id.bookNameTxt);
            authorNameTxt = findViewById(R.id.authorNameTxt);
            pagesNameTxt = findViewById(R.id.pagesNameTxt);
            descriptionTxt = findViewById(R.id.descriptionTxt);

            btnAddCurrently = findViewById(R.id.btnCurrently);
            btnAddWantToReed = findViewById(R.id.btnAddWantToReed);
            btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
            btnAddFavorite = findViewById(R.id.btnFavorite);

            imgBook = findViewById(R.id.imgBook);
        }

        private void setData (Book book){

        bookNameTxt.setText(book.getName());
        authorNameTxt.setText(book.getAuthor());
        pagesNameTxt.setText(String.valueOf(book.getPages()));
        descriptionTxt.setText(book.getLongDesc());
            Glide.with(this)
                    .asBitmap().load(book.getImageURL())
                    .into(imgBook);


        }



}