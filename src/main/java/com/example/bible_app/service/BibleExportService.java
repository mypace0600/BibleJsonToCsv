package com.example.bible_app.service;

import com.example.bible_app.model.Book;
import com.example.bible_app.model.Chapter;
import com.example.bible_app.model.Verse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BibleExportService {

    private static final Map<String, String> BOOK_ABBREVIATIONS = new HashMap<>();

    static {
        BOOK_ABBREVIATIONS.put("창세기", "창");
        BOOK_ABBREVIATIONS.put("출애굽기", "출");
        BOOK_ABBREVIATIONS.put("레위기", "레");
        BOOK_ABBREVIATIONS.put("민수기", "민");
        BOOK_ABBREVIATIONS.put("신명기", "신");
        BOOK_ABBREVIATIONS.put("여호수아", "수");
        BOOK_ABBREVIATIONS.put("사사기", "삿");
        BOOK_ABBREVIATIONS.put("룻기", "룻");
        BOOK_ABBREVIATIONS.put("사무엘상", "삼상");
        BOOK_ABBREVIATIONS.put("사무엘하", "삼하");
        BOOK_ABBREVIATIONS.put("열왕기상", "왕상");
        BOOK_ABBREVIATIONS.put("열왕기하", "왕하");
        BOOK_ABBREVIATIONS.put("역대상", "대상");
        BOOK_ABBREVIATIONS.put("역대하", "대하");
        BOOK_ABBREVIATIONS.put("에스라", "스");
        BOOK_ABBREVIATIONS.put("느헤미야", "느");
        BOOK_ABBREVIATIONS.put("에스더", "에");
        BOOK_ABBREVIATIONS.put("욥기", "욥");
        BOOK_ABBREVIATIONS.put("시편", "시");
        BOOK_ABBREVIATIONS.put("잠언", "잠");
        BOOK_ABBREVIATIONS.put("전도서", "전");
        BOOK_ABBREVIATIONS.put("아가", "아");
        BOOK_ABBREVIATIONS.put("이사야", "사");
        BOOK_ABBREVIATIONS.put("예레미야", "렘");
        BOOK_ABBREVIATIONS.put("예레미야애가", "애");
        BOOK_ABBREVIATIONS.put("에스겔", "겔");
        BOOK_ABBREVIATIONS.put("다니엘", "단");
        BOOK_ABBREVIATIONS.put("호세아", "호");
        BOOK_ABBREVIATIONS.put("요엘", "욜");
        BOOK_ABBREVIATIONS.put("아모스", "암");
        BOOK_ABBREVIATIONS.put("오바댜", "옵");
        BOOK_ABBREVIATIONS.put("요나", "욘");
        BOOK_ABBREVIATIONS.put("미가", "미");
        BOOK_ABBREVIATIONS.put("나훔", "나");
        BOOK_ABBREVIATIONS.put("하박국", "합");
        BOOK_ABBREVIATIONS.put("스바냐", "습");
        BOOK_ABBREVIATIONS.put("학개", "학");
        BOOK_ABBREVIATIONS.put("스가랴", "슥");
        BOOK_ABBREVIATIONS.put("말라기", "말");
        BOOK_ABBREVIATIONS.put("마태복음", "마");
        BOOK_ABBREVIATIONS.put("마가복음", "막");
        BOOK_ABBREVIATIONS.put("누가복음", "눅");
        BOOK_ABBREVIATIONS.put("요한복음", "요");
        BOOK_ABBREVIATIONS.put("사도행전", "행");
        BOOK_ABBREVIATIONS.put("로마서", "롬");
        BOOK_ABBREVIATIONS.put("고린도전서", "고전");
        BOOK_ABBREVIATIONS.put("고린도후서", "고후");
        BOOK_ABBREVIATIONS.put("갈라디아서", "갈");
        BOOK_ABBREVIATIONS.put("에베소서", "엡");
        BOOK_ABBREVIATIONS.put("빌립보서", "빌");
        BOOK_ABBREVIATIONS.put("골로새서", "골");
        BOOK_ABBREVIATIONS.put("데살로니가전서", "살전");
        BOOK_ABBREVIATIONS.put("데살로니가후서", "살후");
        BOOK_ABBREVIATIONS.put("디모데전서", "딤전");
        BOOK_ABBREVIATIONS.put("디모데후서", "딤후");
        BOOK_ABBREVIATIONS.put("디도서", "딛");
        BOOK_ABBREVIATIONS.put("빌레몬서", "몬");
        BOOK_ABBREVIATIONS.put("히브리서", "히");
        BOOK_ABBREVIATIONS.put("야고보서", "약");
        BOOK_ABBREVIATIONS.put("베드로전서", "벧전");
        BOOK_ABBREVIATIONS.put("베드로후서", "벧후");
        BOOK_ABBREVIATIONS.put("요한일서", "요일");
        BOOK_ABBREVIATIONS.put("요한이서", "요이");
        BOOK_ABBREVIATIONS.put("요한삼서", "요삼");
        BOOK_ABBREVIATIONS.put("유다서", "유");
        BOOK_ABBREVIATIONS.put("요한계시록", "계");
    }

    public String getAbbreviation(String fullName) {
        return BOOK_ABBREVIATIONS.getOrDefault(fullName, fullName);
    }
    public void exportToCsv(String jsonFilePath, OutputStream outputStream) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book bibleBook = mapper.readValue(new File(jsonFilePath), Book.class);

        String bookName = bibleBook.getBook();
        String abbreviation = getAbbreviation(bookName);

        try (
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                PrintWriter printWriter = new PrintWriter(bufferedWriter)
        ) {
            // UTF-8 BOM + Header
            printWriter.println('\uFEFF' + "Reference,Text");

            for (Chapter chapter : bibleBook.getChapters()) {
                for (Verse verse : chapter.getVerses()) {
                    String ref = abbreviation + " " + chapter.getChapter() + ":" + verse.getVerse();
                    String text = verse.getText().replace("\"", "\"\""); // CSV-safe
                    printWriter.printf("\"%s\",\"%s\"%n", ref, text);
                }
            }
        }
    }

    public String getCsvFileName(String jsonFilePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Book bibleBook = mapper.readValue(new File(jsonFilePath), Book.class);
        return bibleBook.getBook() + ".csv";
    }
}
