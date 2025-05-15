package com.excelproject.controller;

import com.excelproject.service.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
public class FileController {

    private final ExcelService excelService;

    @PostMapping("/excel/file-upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        excelService.processFile(file);
        return "file successfully uploaded";
    }

    @GetMapping("/excel/write")
    public void writeExcelFile() {
        excelService.generateExcel();
    }
}
