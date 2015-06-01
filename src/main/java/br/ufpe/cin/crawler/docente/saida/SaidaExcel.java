package br.ufpe.cin.crawler.docente.saida;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.ufpe.cin.crawler.ISaida;
import br.ufpe.cin.crawler.docente.Docente;
import br.ufpe.cin.crawler.utils.Log;

class SaidaExcel implements ISaida<Docente> {

	public void imprimir(List<Docente> professores) {
		
		Log.d("Imprimindo excel");

		Collections.sort(professores, new Comparator<Docente>() {
			public int compare(Docente p1, Docente p2) {
				return p1.getNome().compareTo(p2.getNome());
			}
		});

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Professores");

		int rownum = 0;
		for (Docente p : professores) {

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
			cell.setCellValue("P�gina");
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

			String nomeArquivo = Docente.class.getName() + ".xlsx";
			FileOutputStream out = new FileOutputStream(new File(
					nomeArquivo));
			workbook.write(out);
			out.close();
			workbook.close();
			Log.i(nomeArquivo + " finalizado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
