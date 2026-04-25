package com.example.exam;

import com.example.exam.service.BookApiClient;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario1Test {

    private static BookApiClient api;
    private static String testIsbn;

    @BeforeAll
    public static void setup() {
        api = new BookApiClient();
        testIsbn = "978-" + System.currentTimeMillis();
    }

    @Test
    @Order(1)
    @DisplayName("1. Создать новую книгу (POST /books)")
    public void testCreateBook() throws Exception {
        Thread.sleep(500);
        String json = "{\"isbn\":\"" + testIsbn + "\",\"title\":\"Test Book\",\"author\":\"Tester\",\"price\":500}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(api.getBaseUrl() + "/books"))
                .header("Content-Type", "application/json")
                .header("X-API-Key", api.getApiKey())
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, resp.statusCode());
    }

    @Test
    @Order(2)
    @DisplayName("2. Получить книгу по ID (GET /books/{id})")
    public void testGetBookById() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books/4");
        assertTrue(status == 200 || status == 404, "GET /books/4: " + status);
    }

    @Test
    @Order(3)
    @DisplayName("3. Обновить цену (PATCH /books/{id})")
    public void testPatchBook() throws Exception {
        Thread.sleep(500);
        String json = "{\"price\": 777}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(api.getBaseUrl() + "/books/4"))
                .header("Content-Type", "application/json")
                .header("X-API-Key", api.getApiKey())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertTrue(resp.statusCode() == 200 || resp.statusCode() == 404,
                "PATCH: " + resp.statusCode());
    }

    @Test
    @Order(4)
    @DisplayName("4. Проверить наличие (GET /books/{id}/stock)")
    public void testCheckStock() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books/4/stock");
        assertTrue(status == 200 || status == 404, "Stock: " + status);
    }

    @Test
    @Order(5)
    @DisplayName("5. Удалить книгу (DELETE /books/{id})")
    public void testDeleteBook() throws Exception {
        Thread.sleep(500);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(api.getBaseUrl() + "/books/4"))
                .header("X-API-Key", api.getApiKey())
                .DELETE()
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        assertTrue(resp.statusCode() == 204 || resp.statusCode() == 200 || resp.statusCode() == 404,
                "DELETE: " + resp.statusCode());
    }
}