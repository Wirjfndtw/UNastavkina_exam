package com.example.exam.model;

public class Book {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private int year;
    private int price;
    private int stock;
    private double rating;
    private int pages;

    public Book() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author +
                "', price=" + price + ", stock=" + stock + "}";
    }
}
