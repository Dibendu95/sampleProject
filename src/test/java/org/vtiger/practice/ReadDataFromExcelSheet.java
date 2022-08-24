package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelSheet {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(".\\Data\\Campaign Module.xlsx");
       Workbook workbook = WorkbookFactory.create(fis);
       Sheet sh = workbook.getSheet("Sheet11");
       for(int i=1;i<=5;i++)
    	   {
    	   Row row = sh.getRow(1);
       Cell cel = row.getCell(i);
       System.out.println(cel.getStringCellValue());
    	   }
	}

}
