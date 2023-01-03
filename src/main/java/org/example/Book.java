package org.example;

public class Book {
    public String title, author, publisher, year, cat;
    public int price, isbn;
    public Book(int isbn, String title, String author, String pub, String year, String cat, int price)
    {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = pub;
        this.year = year;
        this.cat = cat;
        this.price = price;
    }
}
