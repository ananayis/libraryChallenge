package com.ananayis.mylibrarychallenge;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITES_BOOKS = "favorites_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyRead;
//    private static ArrayList<Book> wantToRead;
//    private static ArrayList<Book> favorite;
//    private static ArrayList<Book> currently;


    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyRead()){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWantToRead()){
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavorite()){
            editor.putString(FAVORITES_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getCurrently()){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //todo: add initData
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "Ansichten eines Clowns", " Heinrich Theodor Böll", 353,"https://m.media-amazon.com/images/I/41w5XkpxO9L.jpg",
                "This book is a novel by West German writer Heinrich Böll.","Following publication in 1963, the novel generated polemics in the press for its negative portrayal of the Catholic Church and the CDU party. Böll's liberal views on religion and social issues inspired the wrath of conservatives in Germany.[1] The conservative press even attacked Böll's 1972 Nobel prize award, arguing that it was awarded only to \"liberals and left-wing radicals."));

        books.add(new Book(2, "Legend", "David Gammell" , 320, "https://i.ebayimg.com/images/g/zsYAAOSwOOZguT01/s-l300.jpg" ,
                "This is the first and most famous novel of this writer.", "The Drenai Empire is under threat. The tribal Nadir people have been united for the first time by the great warleader Ulric, who has forged a massive empire in the North. "));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static synchronized Utils getInstance(Context context) {
        if (null != instance){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null),type);
        return books;
    }

    public ArrayList<Book> getAlreadyRead() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null),type);
        return books;
    }

    public ArrayList<Book> getWantToRead() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null),type);
        return books;
    }

    public ArrayList<Book> getFavorite() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(FAVORITES_BOOKS, null),type);
        return books;
    }

    public ArrayList<Book> getCurrently() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null),type);
        return books;
    }

    public Book getBookById (int id){
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b : books){
                if (b.getId() == id){
                    return b;
                }
            }
        }

        return null;

    }

    public boolean addToAlreadyRead (Book book){
        ArrayList<Book> books = getAlreadyRead();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead (Book book){
        ArrayList<Book> books = getWantToRead();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrently (Book book){
        ArrayList<Book> books = getCurrently();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorite (Book book){
        ArrayList<Book> books = getFavorite();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITES_BOOKS);
                editor.putString(FAVORITES_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToRead();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading (Book book){
        ArrayList<Book> books = getCurrently();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavorite (Book book){
        ArrayList<Book> books = getFavorite();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITES_BOOKS);
                        editor.putString(FAVORITES_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
