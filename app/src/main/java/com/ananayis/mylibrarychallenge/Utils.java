package com.ananayis.mylibrarychallenge;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyRead;
    private static ArrayList<Book> wantToRead;
    private static ArrayList<Book> favorite;
    private static ArrayList<Book> currently;


    private Utils() {

        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyRead){
            alreadyRead = new ArrayList<>();
        }

        if (null == wantToRead){
            wantToRead = new ArrayList<>();
        }

        if (null == favorite){
            favorite = new ArrayList<>();
        }

        if (null == currently){
            currently = new ArrayList<>();
        }
    }

    private void initData() {
        //todo: add initData


        allBooks.add(new Book(1, "Ansichten eines Clowns", " Heinrich Theodor Böll", 353,"https://m.media-amazon.com/images/I/41w5XkpxO9L.jpg",
                "This book is a novel by West German writer Heinrich Böll.","Following publication in 1963, the novel generated polemics in the press for its negative portrayal of the Catholic Church and the CDU party. Böll's liberal views on religion and social issues inspired the wrath of conservatives in Germany.[1] The conservative press even attacked Böll's 1972 Nobel prize award, arguing that it was awarded only to \"liberals and left-wing radicals."));

        allBooks .add(new Book(2, "Legend", "David Gammell" , 320, "https://i.ebayimg.com/images/g/zsYAAOSwOOZguT01/s-l300.jpg" ,
                "This is the first and most famous novel of this writer.", "The Drenai Empire is under threat. The tribal Nadir people have been united for the first time by the great warleader Ulric, who has forged a massive empire in the North. "));


    }

    public static synchronized Utils getInstance() {
        if (null != instance){
            return instance;
        }else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyRead() {
        return alreadyRead;
    }

    public static ArrayList<Book> getWantToRead() {
        return wantToRead;
    }

    public static ArrayList<Book> getFavorite() {
        return favorite;
    }

    public static ArrayList<Book> getCurrently() {
        return currently;
    }

    public Book getBookById (int id){
        for (Book b : allBooks){
            if (b.getId() == id){
                return b;
            }
        }
        return null;

    }

    public boolean addToAlreadyRead (Book book){
        if (!alreadyRead.contains(book)){
            return alreadyRead.add(book);
        }
        return false;
    }

    public boolean addToWantToRead (Book book){
        if (!wantToRead.contains(book)){
            return wantToRead.add(book);
        }
        return false;
    }

    public boolean addToCurrently (Book book){
        if (!currently.contains(book)){
            return currently.add(book);
        }
        return false;
    }

    public boolean addToFavorite (Book book){
        if (!favorite.contains(book)){
            return favorite.add(book);
        }
        return false;
    }
}
