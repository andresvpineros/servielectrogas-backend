package co.com.msservielectrogas.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class CreatedExcel {
	
	public static ByteArrayInputStream writeExcel(List<String> datos) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Reporte de rutero");
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        String[] titulos = { "Fecha de Programación", "Id Orden De Servicio", "Id Orden Servicio Proveedor","Nombre del cliente",
					"Documento Cliente", "Telefono", "Telefono 1", "Telefono 2", "Dirección", "Tecnico Asignado",
					"Tipo de Servicio Solicitado", "Servicio Solicitado"};
	        int indiceFila = 0;
			Row fila = sheet.createRow(indiceFila);
	        for (int i = 0; i < titulos.length; i++) {
				String encabezado = titulos[i];
				Cell celda = fila.createCell(i);
				celda.setCellValue(encabezado);
				celda.setCellStyle(headerCellStyle);
			}
	        
	        for(int i = 0; i < datos.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	String[] list = datos.get(i).split(",");
	        	dataRow.createCell(0).setCellValue(list[0]);
	        	dataRow.createCell(1).setCellValue(list[1]);
	        	dataRow.createCell(2).setCellValue(list[2]);
	        	dataRow.createCell(3).setCellValue(list[3]);
	        	dataRow.createCell(4).setCellValue(list[4]);
	        	dataRow.createCell(5).setCellValue(list[5]);
	        	dataRow.createCell(6).setCellValue(list[6]);
	        	dataRow.createCell(7).setCellValue(list[7]);
	        	dataRow.createCell(8).setCellValue(list[8]);
	        	dataRow.createCell(9).setCellValue(list[9]);
	        	dataRow.createCell(10).setCellValue(list[10]);
	        	dataRow.createCell(11).setCellValue(list[11]);
				indiceFila++;
	        }        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
