package com.ananayis.mylibrarychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

public class BookActivity extends AppCompatActivity {

    private TextView bookNameTxt, authorNameTxt, pagesNameTxt, descriptionTxt;
    private Button btnAddCurrently, btnAddWantToReed, btnAlreadyRead, btnAddFavorite;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        Book book = new Book(2, "Legend", "David Gammell" , 320, "https://i.ebayimg.com/images/g/zsYAAOSwOOZguT01/s-l300.jpg" ,
//                "This is the first and most famous novel of this writer.", "The Drenai Empire is under threat. The tribal Nadir people have been united for the first time by the great warleader Ulric, who has forged a massive empire in the North. ");

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToRead(incomingBook);
                    handleCurrently(incomingBook);
                    handleFavorite(incomingBook);
                }

            }
        }

    }

        private void initViews(){
            bookNameTxt = findViewById(R.id.bookNameTxt);
            authorNameTxt = findViewById(R.id.authorNameTxt);
            pagesNameTxt = findViewById(R.id.pagesNameTxt);
            descriptionTxt = findViewById(R.id.descriptionTxt);

            btnAddCurrently = findViewById(R.id.btnAddCurrently);
            btnAddWantToReed = findViewById(R.id.btnAddWantToReed);
            btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
            btnAddFavorite = findViewById(R.id.btnAddFavorite);

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

        private void handleAlreadyRead(Book book){

            ArrayList<Book> alreadyReadBook = Utils.getInstance(this).getAlreadyRead();

            boolean existInAlreadyReadBooks = false;

            for (Book b: alreadyReadBook){
                if (b.getId() == book.getId()) {
                    existInAlreadyReadBooks = true;
                }
            }
            if (existInAlreadyReadBooks){
                btnAlreadyRead.setEnabled(false);
            }else {
                btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                            Toast.makeText(BookActivity.this,"Book added",Toast.LENGTH_SHORT).show();

                            btnAlreadyRead.setEnabled(false);
                            Intent intent = new Intent(BookActivity.this, AlreadyReadActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BookActivity.this,"Try one more time",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        private void handleWantToRead(final Book book){
            ArrayList<Book> wantToRead = Utils.getInstance(this).getWantToRead();

            boolean existInWantToReadBooks = false;

            for (Book b: wantToRead){
                if (b.getId() == book.getId()) {
                    existInWantToReadBooks = true;
                }
            }
            if (existInWantToReadBooks){
                btnAddWantToReed.setEnabled(false);
            }else {
                btnAddWantToReed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                            Toast.makeText(BookActivity.this,"Book added",Toast.LENGTH_SHORT).show();

                            btnAddWantToReed.setEnabled(false);
                            Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BookActivity.this,"Try one more time",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        private void handleCurrently(final Book book){
            ArrayList<Book> currently = Utils.getInstance(this).getCurrently();

            boolean existInCurrently = false;

            for (Book b: currently){
                if (b.getId() == book.getId()) {
                    existInCurrently = true;
                }
            }
            if (existInCurrently){
                btnAddCurrently.setEnabled(false);
            }else {
                btnAddCurrently.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.getInstance(BookActivity.this).addToCurrently(book)){
                            Toast.makeText(BookActivity.this,"Book added",Toast.LENGTH_SHORT).show();

                            btnAddWantToReed.setEnabled(false);
                            Intent intent = new Intent(BookActivity.this, CurrentlyActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BookActivity.this,"Try one more time",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        private void handleFavorite(final Book book){
            ArrayList<Book> favorite = Utils.getInstance(this).getFavorite();

            boolean existInFavorite = false;

            for (Book b: favorite){
                if (b.getId() == book.getId()) {
                    existInFavorite = true;
                }
            }
            if (existInFavorite){
                btnAddFavorite.setEnabled(false);
            }else {
                btnAddFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.getInstance(BookActivity.this).addToFavorite(book)){
                            Toast.makeText(BookActivity.this,"Book added",Toast.LENGTH_SHORT).show();

                            btnAddFavorite.setEnabled(false);
                            Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BookActivity.this,"Try one more time",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

}