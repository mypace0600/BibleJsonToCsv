package com.example.bible_app.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private String book;
    private List<Chapter> chapters;
}