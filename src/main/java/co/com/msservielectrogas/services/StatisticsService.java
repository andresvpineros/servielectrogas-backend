package co.com.msservielectrogas.services;

import co.com.msservielectrogas.dto.*;
import co.com.msservielectrogas.repository.StatisticsRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<TechnicianStatisticsDTO> getTechnicianStatistics(LocalDate date) {
        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = startDate.plusDays(1);
        return statisticsRepository.getTechnicianStatistics(startDate, endDate);
    }

    public List<TechnicianStatisticsDTO> getWarrantyTechnicianStatistics() {
        return statisticsRepository.getWarrantyTechnicianStatistics();
    }

    public List<WarrantyStatisticsDTO> getWarrantyStatistics() {
        return statisticsRepository.getWarrantyStatistics();
    }

    public List<co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO> getServiceTypeStatistics() {
        return statisticsRepository.getServiceTypeStatistics();
    }

    public List<TechnicianEffectivenessDTO> getTechnicianEffectivenessStatistics() {
        return statisticsRepository.getTechnicianEffectivenessStatistics();
    }

    public List<WarrantiesByTechnicianDTO> getWarrantiesByTechnician() {
        return statisticsRepository.getWarrantiesByTechnician();
    }

    public List<WarrantiesByTypeDTO> getWarrantiesByType() {
        return statisticsRepository.getWarrantiesByType();
    }

    public List<ServiceReportDTO> getServiceReports() {
        System.out.println("Fetching service reports from the repository");
        List<ServiceReportDTO> reports = statisticsRepository.getServiceReports();
        System.out.println("Fetched " + reports.size() + " reports");
        return reports;
    }

    public ByteArrayInputStream exportServiceReportsToExcel() throws IOException {
        List<ServiceReportDTO> reports = getServiceReports();
        System.out.println("Exporting " + reports.size() + " reports to Excel");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Service Reports");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Names");
            headerRow.createCell(1).setCellValue("Name of Applicant");
            headerRow.createCell(2).setCellValue("Service Description");
            headerRow.createCell(3).setCellValue("Total Charged");
            headerRow.createCell(4).setCellValue("Service Date");

            int rowNum = 1;
            for (ServiceReportDTO report : reports) {
                System.out.println("Processing report: " + report);
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(report.getTechnicianName());
                row.createCell(1).setCellValue(report.getClientName());
                row.createCell(2).setCellValue(report.getObservations());
                row.createCell(3).setCellValue(report.getTotalCharged());
                row.createCell(4).setCellValue(report.getCreatedAt().toString());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
    
    public List<TechnicianSettlementDTO> getTechnicianSettlements() {
        System.out.println("Fetching technician settlements from the repository");
        List<TechnicianSettlementDTO> settlements = statisticsRepository.getTechnicianSettlements();
        System.out.println("Fetched " + settlements.size() + " settlements");
        return settlements;
    }

    public ByteArrayInputStream exportTechnicianSettlementsToExcel() throws IOException {
        List<TechnicianSettlementDTO> settlements = getTechnicianSettlements();
        System.out.println("Exporting " + settlements.size() + " settlements to Excel");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Technician Settlements");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue(".");
            headerRow.createCell(1).setCellValue("Nombres");
            headerRow.createCell(2).setCellValue("Apellidos"); // Ajustar según la disponibilidad del dato
            headerRow.createCell(3).setCellValue("Total Cobrado");
            headerRow.createCell(4).setCellValue("Mes");

            int rowNum = 1;
            for (TechnicianSettlementDTO settlement : settlements) {
                System.out.println("Processing settlement: " + settlement);
                System.out.println("Document: " + settlement.getDocument());
                System.out.println("Name: " + settlement.getName());
                System.out.println("Total Charged: " + settlement.getTotalCharged());
                System.out.println("Month: " + settlement.getMonth());

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(settlement.getDocument());
                row.createCell(1).setCellValue(settlement.getName());
                // Asumiendo que "LastNames" no está disponible en este contexto, se omite en la creación del archivo.
                row.createCell(2).setCellValue(""); // Dejar vacío o ajustar según necesidad.
                row.createCell(3).setCellValue(settlement.getTotalCharged());
                row.createCell(4).setCellValue(settlement.getMonth());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}
