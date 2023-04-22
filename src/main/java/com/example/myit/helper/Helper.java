package com.example.myit.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.myit.pojo.Product;

public class Helper {
	// check excel formate
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
//			                    application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
			return true;
		} else {
			return false;
		}
	}

//convert excel to list of product 
	public List<Product> convertListToProduct(InputStream is) {
		List<Product> list = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("sheet1");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Product p = new Product();
				int cellid = 0;
				Iterator<Cell> cells = row.iterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cellid) {
					case 0:
						p.setProductId((int) cell.getNumericCellValue());
						break;

					case 1:
						p.setProductName(cell.getStringCellValue());
						break;

					case 2:
						p.setProductDesc(cell.getStringCellValue());
						break;

					case 3:
						p.setProductPrice((long) cell.getNumericCellValue());
						break;

					default:
						throw new IllegalArgumentException("Unexpected value: " + cellid);
					}
					cellid++;
				}
				list.add(p);
			}

		} catch (Exception e) {
			System.out.println();
		}
		return list;
	}

}
