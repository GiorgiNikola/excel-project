package com.excelproject.controller;

import com.excelproject.service.ExcelService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @GetMapping("/excel/download")
    public ResponseEntity<byte[]> downloadExcelFile() {
        byte[] excelBytes = excelService.generateExcelByteArr();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.attachment().filename("departments.xlsx").build());

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
}
