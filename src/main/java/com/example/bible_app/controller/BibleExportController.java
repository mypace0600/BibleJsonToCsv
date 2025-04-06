package com.example.bible_app.controller;

import com.example.bible_app.service.BibleExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Controller
@RequiredArgsConstructor
public class BibleExportController {

    private final BibleExportService service;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportBibleCsv(@RequestParam String jsonPath) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            service.exportToCsv(jsonPath, out);

            String fileName = service.getCsvFileName(jsonPath);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
