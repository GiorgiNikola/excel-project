# Excel Project 📊

This Spring Boot project provides functionality to:
- Upload and process employee data from an Excel (.xlsx) file
- Calculate average salaries per department
- Generate and download a new Excel file containing summarized department salary data

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Apache POI (for Excel processing)
- Postman or any HTTP client (for testing)

---

## 📂 Features

### ✅ Upload Excel File
Parses a user-uploaded Excel file containing employee data.

- **Endpoint**: `POST /excel/file-upload`
- **Body**: Multipart form-data with field `file`
- **Excel Format**:
- **| Name | Surname | Email | Department | Salary |**

### ✅ Generate Excel Summary
Creates an Excel file (`temp.xlsx`) that includes average salaries by department.

- **Endpoint**: `GET /excel/write`

### ✅ Download Excel File
Downloads the previously generated summary Excel file.

- **Endpoint**: `GET /excel/download`
- **Response**: Downloadable `.xlsx` file with headers:
- **| Department | Average Salary |**
