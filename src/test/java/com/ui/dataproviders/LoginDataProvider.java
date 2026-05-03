package com.ui.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtil;
import com.utility.ExcelReaderUtil;

public class LoginDataProvider {
	private TestData data;
	@DataProvider(name = "loginCredentials")
	public Object[][] getCredentials(){
		ObjectMapper objectMapper = new ObjectMapper();
		Object[][] credentials = null;
		try {
		File testDataJSON = new File(System.getProperty("user.dir")
				+File.separator+"test-data"+
				File.separator+"loginData.json");
		data = objectMapper.readValue(testDataJSON, TestData.class);
		List<User> users = data.getData();
		credentials = new Object[users.size()][1];
		for(int i =0; i < users.size();i++) {
			credentials[i][0] = users.get(i);
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return credentials;
	}
	
	@DataProvider(name = "credentials")
	public Iterator<Object[]> getLoginCredentials() throws Exception, IOException{
		List<Object[]> credentialsList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File(System.getProperty("user.dir")+File.separator+"test-data"+File.separator+"loginData.json");
		data = objectMapper.readValue(jsonFile, TestData.class);
		List<User> users = data.getData();
		for(User user: users) {
			credentialsList.add(new Object[] {user});
		}
		return credentialsList.iterator();
		
	}
	
	@DataProvider(name = "credsFromCSV")
	public Iterator<User> getCredentialsData(){
		return CSVReaderUtil.getCSVData("testDataLogin.csv");
	}
	
	@DataProvider(name ="credentialsFromXLSX")
	public Iterator<User> getCredentialsDataFromXLSX(){
		return ExcelReaderUtil.getDataFromXLSXFile("loginData.xlsx");
	}
}
