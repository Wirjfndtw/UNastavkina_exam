package com.example.exam;

import com.example.exam.service.BookApiClient;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario2Test {

    private static BookApiClient api;

    @BeforeAll
    public static void setup() {
        api = new BookApiClient();
    }

    @Test
    @Order(1)
    @DisplayName("1. Проверить наличие книги (GET /books/{id}/stock)")
    public void testCheckStock() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books/5/stock");
        assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("2. Добавить отзыв на книгу (POST /books/{id}/reviews)")
    public void testAddReview() throws Exception {
        Thread.sleep(500);
        String json = "{\"rating\":5,\"comment\":\"Отличная книга!\",\"reviewerName\":\"Студент\"}";
        java.net.http.HttpRequest req = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(api.getBaseUrl() + "/books/5/reviews"))
                .header("Content-Type", "application/json")
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(json))
                .build();
        java.net.http.HttpResponse<String> resp = java.net.http.HttpClient.newHttpClient()
                .send(req, java.net.http.HttpResponse.BodyHandlers.ofString());
        assertEquals(201, resp.statusCode());
    }
}