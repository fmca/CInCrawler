package br.ufpe.cin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static void escreverExcel(List<Professor> professores) {

		Collections.sort(professores, new Comparator<Professor>() {
			public int compare(Professor p1, Professor p2) {
				return p1.getNome().compareTo(p2.getNome());
			}
		});

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Professores");

		int rownum = 0;
		for (Professor p : professores) {

			Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue("Nome");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getNome());

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Email");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getEmail());

			String[] areas = p.getAreasInteresse();
			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Areas");
			for (String a : areas) {
				cell = row.createCell(cellnum++);
				cell.setCellValue(a);
			}

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Lattes");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getLattes());

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Página");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getPagina());

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Fone");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getFone());

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Fax");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getFax());

			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellValue("Imagem");
			cell = row.createCell(cellnum++);
			cell.setCellValue(p.getUrlImagem());
			row = sheet.createRow(rownum++);

		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(
					"professores.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Arquivo excel construído com êxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
