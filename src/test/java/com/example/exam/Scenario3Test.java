package com.example.exam;

import com.example.exam.service.BookApiClient;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Scenario3Test {

    private static BookApiClient api;

    @BeforeAll
    public static void setup() {
        api = new BookApiClient();
    }

    @Test
    @Order(1)
    @DisplayName("1. Получить книги с фильтром по жанру")
    public void testFilterByGenre() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books?genre=Classic");
        assertEquals(200, status);
    }

    @Test
    @Order(2)
    @DisplayName("2. Получить книги с пагинацией")
    public void testPagination() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books?page=0&size=3");
        assertEquals(200, status);
    }

    @Test
    @Order(3)
    @DisplayName("3. Получить книги с фильтром по цене")
    public void testFilterByPrice() throws Exception {
        Thread.sleep(500);
        int status = api.getStatus("/books?minPrice=400&maxPrice=600");
        assertEquals(200, status);
    }
}