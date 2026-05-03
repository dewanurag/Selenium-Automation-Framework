package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtil {

	public static Iterator<User> getCSVData(String fileName) {
		File csvFile = new File(System.getProperty("user.dir")+File.separator+"test-data"+File.separator+fileName);
		FileReader reader =null;
		CSVReader csvReader ;
		String[] line;
		List<User> userlist=null; 
		try {
			reader = new FileReader(csvFile);
			csvReader = new CSVReader(reader);
			csvReader.readNext();//read line by line
//			data = csvReader.readNext(); //read line 2 as line1 was already read
//			//if there is now row or we have reached the end it would return "null"
//			System.out.println(Arrays.toString(data));
			userlist = new ArrayList<>();
			while((line = csvReader.readNext())!=null) {
				User user = new User(line[0],line[1]);
				userlist.add(user);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e ) {
			e.printStackTrace();
		} 
		catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userlist.iterator();
		
		
	}
	
}
