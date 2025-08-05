package genericUtilityOrangeHRM;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String toGetDataFromExcelFile(String sheetname, int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\OrangeHRM.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	
	

}
