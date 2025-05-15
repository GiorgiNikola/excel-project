package com.excelproject.service;

import com.excelproject.model.Employee;
import jakarta.servlet.ServletOutputStream;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExcelService {

    EmployeeService employeeService;

    public void processFile(MultipartFile file) {
        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            employeeService.employees = new HashMap<>();
            int rowNum = 0;
            for(Row row : sheet) {
                if(rowNum == 0) {
                    rowNum++;
                    continue;
                }
                List<String> employeeDetails = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING -> employeeDetails.add(cell.getStringCellValue());
                        case NUMERIC -> employeeDetails.add(String.valueOf(cell.getNumericCellValue()));
                    }
                }
                Employee employee = new Employee()
                        .setName(employeeDetails.get(0))
                        .setSurname(employeeDetails.get(1))
                        .setEmail(employeeDetails.get(2))
                        .setDepartment(employeeDetails.get(3))
                        .setSalary(Double.parseDouble(employeeDetails.get(4)));
                employeeService.employees.put(employee.getEmail(), employee);
                rowNum++;
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void generateExcel() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();

            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 6000);

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Department");
            row.createCell(1).setCellValue("Average Salary");
            Map<String,Double> averageSalary = employeeService.getDepartmentsAverageSalary();
            int rowNum = 1;
            for (Map.Entry<String, Double> entry : averageSalary.entrySet()) {
                row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public byte[] generateExcelByteArr() {
        File file = new File("temp.xlsx");

        if (!file.exists()) {
            throw new RuntimeException("Excel file not found: " + file.getAbsolutePath());
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }
}
