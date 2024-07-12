package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.dto.*;
import co.com.msservielectrogas.services.StatisticsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/technician-orders")
    public List<TechnicianStatisticsDTO> getTechnicianStatistics(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return statisticsService.getTechnicianStatistics(date);
    }

    @GetMapping("/warranty-technician")
    public List<TechnicianStatisticsDTO> getWarrantyTechnicianStatistics() {
        return statisticsService.getWarrantyTechnicianStatistics();
    }

    @GetMapping("/service-types")
    public List<ServiceTypeStatisticsDTO> getServiceTypeStatistics() {
        return statisticsService.getServiceTypeStatistics();
    }

    @GetMapping("/warranty-types")
    public List<WarrantyStatisticsDTO> getWarrantyStatistics() {
        return statisticsService.getWarrantyStatistics();
    }

    @GetMapping("/technician-effectiveness")
    public List<TechnicianEffectivenessDTO> getTechnicianEffectivenessStatistics() {
        return statisticsService.getTechnicianEffectivenessStatistics();
    }

    @GetMapping("/warranties-by-technician")
    public List<WarrantiesByTechnicianDTO> getWarrantiesByTechnician() {
        return statisticsService.getWarrantiesByTechnician();
    }

    @GetMapping("/warranties-by-type")
    public List<WarrantiesByTypeDTO> getWarrantiesByType() {
        return statisticsService.getWarrantiesByType();
    }

    @GetMapping("/service-report")
public ResponseEntity<byte[]> exportServiceReportToExcel() throws IOException {
    System.out.println("Request received to export service reports to Excel");
    List<ServiceReportDTO> reportData = statisticsService.getServiceReports();
    System.out.println("Report data: " + reportData);

    try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("Service Report");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue(".");
        headerRow.createCell(1).setCellValue("Nombres");
        headerRow.createCell(2).setCellValue("Apellidos");
        headerRow.createCell(3).setCellValue("Tipo de Servicio");
        headerRow.createCell(4).setCellValue("Valor Cobrado");
        headerRow.createCell(5).setCellValue("Fecha de Ejecución");

        int rowNum = 1;
        for (ServiceReportDTO data : reportData) {
            System.out.println("Processing report data: " + data);
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(data.getTechnicianName());
            row.createCell(2).setCellValue(data.getClientName());
            row.createCell(3).setCellValue(data.getObservations());
            row.createCell(4).setCellValue(data.getTotalCharged());
            row.createCell(5).setCellValue(data.getCreatedAt().toString());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=service_report.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }
}


    @GetMapping("/technician-settlement")
    public ResponseEntity<byte[]> exportTechnicianSettlementToExcel() throws IOException {
        System.out.println("Request received to export technician settlements to Excel");
        ByteArrayInputStream in = statisticsService.exportTechnicianSettlementsToExcel();
        System.out.println("Excel file generated successfully");
        return createExcelResponseEntity(in, "technician_settlement.xlsx");
    }

    private ResponseEntity<byte[]> createExcelResponseEntity(ByteArrayInputStream in, String fileName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        System.out.println("Returning the Excel file as response");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(outputStream.toByteArray());
    }
}
