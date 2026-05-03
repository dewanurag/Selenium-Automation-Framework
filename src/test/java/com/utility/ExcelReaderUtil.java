package com.utility;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;
public class ExcelReaderUtil {
	
	public static Iterator<User> getDataFromXLSXFile(String fileName) {
		//XLSX file
		File xlsxFile = new File(System.getProperty("user.dir")+File.separator+"test-data"+File.separator+fileName);
		List<User> userList = new ArrayList<>();
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		XSSFWorkbook xssfWorkbook;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			XSSFSheet loginDataSheet = xssfWorkbook.getSheet("loginData");
			Iterator<Row> rowIterator = loginDataSheet.rowIterator();
			rowIterator.next();
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				userList.add(new User(emailAddressCell.toString(), passwordCell.toString()));
			}
			xssfWorkbook.close();
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
		
	}

}
