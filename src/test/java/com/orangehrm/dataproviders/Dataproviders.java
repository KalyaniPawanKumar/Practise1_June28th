package com.orangehrm.dataproviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.orangehrm.base.Base;

public class Dataproviders extends Base{

	
	@DataProvider(name ="dataFromExcel")
	public static Object[][] dataFromExcel() throws Exception {
		
		File f = new File("./data/testvalues.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheet("testdata");
		int rows=ws.getLastRowNum();
		
		Object[][] obj= new String[rows+1][2];
		
		for(int i=0; i<= rows; i++) {
			String username = ws.getRow(i).getCell(0).getStringCellValue();
			String password = ws.getRow(i).getCell(1).getStringCellValue();
			
			obj[i][0]= username;
			obj[i][1] = password;
			
			System.out.println(username);
			System.out.println(password);
		}
		wb.close();
		
		return obj;
		
		
		
	}

}
