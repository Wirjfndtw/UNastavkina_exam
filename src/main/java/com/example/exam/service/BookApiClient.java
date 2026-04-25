package com.example.exam.service;

import com.example.exam.config.ApiConfig;
import com.example.exam.model.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookApiClient {

    private final HttpClient client;
    private final String baseUrl;
    private final String apiKey;

    public BookApiClient() {
        this.client = HttpClient.newHttpClient();
        this.baseUrl = ApiConfig.BASE_URL;
        this.apiKey = ApiConfig.API_KEY;
    }

    public String getAllBooks() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "GET /books -> " + response.statusCode() + "\n" + response.body();
    }

    public String getBookById(long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + id))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "GET /books/" + id + " -> " + response.statusCode() + "\n" + response.body();
    }

    public String getBookByIsbn(String isbn) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/isbn/" + isbn))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "GET /books/isbn/" + isbn + " -> " + response.statusCode() + "\n" + response.body();
    }

    public String createBook(String isbn, String title, String author, int price) throws Exception {
        String json = "{ \"isbn\": \"" + isbn + "\", \"title\": \"" + title +
                "\", \"author\": \"" + author + "\", \"price\": " + price + " }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books"))
                .header("Content-Type", "application/json")
                .header("X-API-Key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "POST /books -> " + response.statusCode() + "\n" + response.body();
    }

    public String updateBook(long id, String isbn, String title, String author,
                             String genre, int year, int price, int stock, int pages) throws Exception {
        String json = "{ \"isbn\": \"" + isbn + "\", \"title\": \"" + title +
                "\", \"author\": \"" + author + "\", \"genre\": \"" + genre +
                "\", \"year\": " + year + ", \"price\": " + price +
                ", \"stock\": " + stock + ", \"pages\": " + pages + " }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + id))
                .header("Content-Type", "application/json")
                .header("X-API-Key", apiKey)
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "PUT /books/" + id + " -> " + response.statusCode() + "\n" + response.body();
    }

    public String deleteBook(long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + id))
                .header("X-API-Key", apiKey)
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "DELETE /books/" + id + " -> " + response.statusCode() + "\n" + response.body();
    }

    public String getStock(long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + id + "/stock"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "GET /books/" + id + "/stock -> " + response.statusCode() + "\n" + response.body();
    }

    public String addReview(long bookId, int rating, String comment, String reviewerName) throws Exception {
        String json = "{ \"rating\": " + rating +
                ", \"comment\": \"" + comment + "\"" +
                ", \"reviewerName\": \"" + reviewerName + "\" }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + bookId + "/reviews"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "POST /books/" + bookId + "/reviews -> " + response.statusCode() + "\n" + response.body();
    }

    public String getReviews(long bookId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books/" + bookId + "/reviews"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "GET /books/" + bookId + "/reviews -> " + response.statusCode() + "\n" + response.body();
    }

    public int getStatus(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public int createBookAndGetStatus(String isbn, String title, String author, int price) throws Exception {
        String json = "{ \"isbn\": \"" + isbn + "\", \"title\": \"" + title +
                "\", \"author\": \"" + author + "\", \"price\": " + price + " }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/books"))
                .header("Content-Type", "application/json")
                .header("X-API-Key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public String getBaseUrl() {
        return baseUrl;
    }
    public String getApiKey() {
        return apiKey;
    }
}
